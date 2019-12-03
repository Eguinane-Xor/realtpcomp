package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//            produitRepository.deleteAllInBatch(); //permet de clean les databases
//            userRepository.deleteAllInBatch();    //lui aussi
            if(userRepository.count()==0) {
                BCryptPasswordEncoder crypter = new BCryptPasswordEncoder();
                System.out.println("Initialisation BD User");
                userRepository.save(new User("Xoreas", crypter.encode("user"),TypeUser.loueur));
                userRepository.save(new User("Eguinane", crypter.encode("user"),TypeUser.locataire));
            }
            if(produitRepository.count()==0){
                System.out.println("Initialisation BD Produit");
                produitRepository.save(new Produit(737, TypeProduit.maison,
                        "3 Quartier Branly, Douchy-Les-Mines",6,
                        userRepository.findByUsername("Xoreas").getId()));
                produitRepository.save(new Produit(420,TypeProduit.appartement,
                        "25 Rue Anthony Matthieu, Valenciennes",2,
                        userRepository.findByUsername("Xoreas").getId()));
            }
        };
    }

}