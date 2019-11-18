package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pack.usr.TypeUser;
import pack.usr.User;
import pack.usr.UserRepository;

@SpringBootApplication  //already applies @EnableAutoConfiguration
public class Application {

    @Autowired
    private UserRepository userRepository;

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
        };
    }

}