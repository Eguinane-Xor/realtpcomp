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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner initialize() {
        return (args) -> {
            if(userRepository.count()==0) {
                System.out.println("Initialisation BD User");
                userRepository.save(new User("Xoreas", "user",TypeUser.loueur));
                userRepository.save(new User("Eguinane", "user",TypeUser.locataire));
            }
            if(produitRepository.count()==0){
                System.out.println("Initialisation BD Produit");
                produitRepository.save(new Produit(737, TypeProduit.maison,
                        "3 Quartier Branly, Douchy-Les-Mines",6));
                produitRepository.save(new Produit(420,TypeProduit.appartement,
                        "25 Rue Anthony Matthieu, Valenciennes",2));
            }
        };
    }

}