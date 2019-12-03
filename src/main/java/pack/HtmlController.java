package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.prod.Produit;
import pack.prod.ProduitRepository;
import pack.usr.User;
import pack.usr.UserRepository;

import java.util.List;

@Controller
public class HtmlController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("*")
    public String welcome(Model model) {
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits",produits);
        return "index";
    }

    public String test(){
        return "test";
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
    public String my_account(){
        return "my_account";
    }

}