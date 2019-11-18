package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import pack.usr.User;

@Controller
public class HtmlController {

    @GetMapping("")
    public String init(Model model) {
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/connexion")
    public String button(Model model){
        return "connexion";
    }



}