package pack;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/connexion")
    public String connexion(){
        return "connexion";
    }

    @GetMapping("/connecter")
    public String connecter(){
        return "index";
    }

    @GetMapping("/inscription")
    public String inscription(Model model){
        model.addAttribute("user",new User());
        return "inscription";
    }
    @PostMapping("/inscription")
    public String inscrire(@ModelAttribute("user") User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getType());
        return "index";
    }

}