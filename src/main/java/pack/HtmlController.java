package pack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/index")
    public String init(Model model) {
        return "index";
    }

    @GetMapping("/connexion")
    public String connexion(Model model){
        return "connexion";
    }

    @GetMapping("/connecter")
    public String connecter(Model model){
        return "connexion";
    }

    @GetMapping("/inscription")
    public String inscription(Model model){
        return "inscription";
    }

    @GetMapping("/inscrire")
    public String inscrire(Model model){
        return "index";
    }

}