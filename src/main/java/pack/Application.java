package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pack.prod.Produit;
import pack.prod.ProduitRepository;
import pack.prod.TypeProduit;
import pack.usr.TypeUser;
import pack.usr.User;
import pack.usr.UserRepository;

@SpringBootApplication  //already applies @EnableAutoConfiguration
public class Application {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProduitRepository produitRepository;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner initialize() {
        return (args) -> {
            System.out.println("Initialisation BD");
            if(userRepository.count()==0) {
                userRepository.save(new User(TypeUser.loueur, "Xoreas", "user"));
                userRepository.save(new User(TypeUser.locataire, "Eguinane", "user"));
            }
            if(produitRepository.count()==0){
                produitRepository.save(new Produit(737, TypeProduit.maison,
                        "3 Quartier Branly, Douchy-Les-Mines",6));
                produitRepository.save(new Produit(420,TypeProduit.appartement,
                        "25 Rue Anthony Matthieu, Valenciennes",2));
            }
        };
    }

}