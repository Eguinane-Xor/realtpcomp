package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.prod.Produit;
import pack.prod.ProduitRepository;
import pack.usr.User;
import pack.usr.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HtmlController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("*")
    public String welcome(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toList()).size() > 1) {
            String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toList()).get(1);
            model.addAttribute("role", role);
        } else{
            model.addAttribute("role", "NONE");
        }
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits",produits);
        return "index";
    }

    @PostMapping("/abonner")
    public String Suscribe(@RequestParam("action") int id, Model model){
        String idLocataire = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toList()).get(0);
        Produit produit = produitRepository.findByID(id);
        produit.setIDlocataire(Integer.parseInt(idLocataire));
        produit.setEnAttente(true);
        produitRepository.save(produit);//update the product
        return welcome(model);
    }
    @PostMapping("/confirmer")
    public String Confirm(@RequestParam("action") int id, Model model){
        Produit produit = produitRepository.findByID(id);
        produit.setEnAttente(false);
        produitRepository.save(produit);//update the product
        return welcome(model);
    }
    @PostMapping("/refuser")
    public String Refuse(@RequestParam("action") int id, Model model){
        Produit produit = produitRepository.findByID(id);
        produit.setEnAttente(false);
        produit.setIDlocataire(-1);
        produitRepository.save(produit);//update the product
        return welcome(model);
    }

    @GetMapping("/connexion")
    public String connexion(Model model){
        if(!model.containsAttribute("user"))
            model.addAttribute("user",new User());
        return "connexion";
    }

    @GetMapping("/inscription")
    public String inscription(Model model){
        if(!model.containsAttribute("user"))
            model.addAttribute("user",new User());
        return "inscription";
    }
    @PostMapping("/inscription")
    public String inscrire(@ModelAttribute("user") User user,Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null){ //non null si username deja dans db
        return "inscription";
    }else {
            BCryptPasswordEncoder crypter = new BCryptPasswordEncoder();
            user.setPassword(crypter.encode(user.getPassword()));
            userRepository.save(user);
            return welcome(model);
        }
    }

    @GetMapping("/my_account")
    public String my_account(Model model){
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits",produits);
        //add role of the user to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toList());
//        for(int i=0;i<roles.size();i++)
//            System.out.println(roles.get(i));
        model.addAttribute("id",roles.get(0));
        model.addAttribute("role",roles.get(1));//j'ai triché j'ai passé l'id dans les droits
        return "my_account";
    }

    @PostMapping("/supprimer")
    public String delete(@RequestParam("action") int id,Model model){
        Produit produit = produitRepository.findByID(id);
        produitRepository.delete(produit);
        return my_account(model);
    }
    @PostMapping("/desabonner")
    public String unsuscribe(@RequestParam("action") int id,Model model){
        Produit produit = produitRepository.findByID(id);
        produit.setIDlocataire(-1);
        produitRepository.save(produit);
        return my_account(model);
    }

}