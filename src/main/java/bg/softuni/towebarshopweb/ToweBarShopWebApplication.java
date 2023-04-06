package bg.softuni.towebarshopweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToweBarShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToweBarShopWebApplication.class, args);
    }

}
