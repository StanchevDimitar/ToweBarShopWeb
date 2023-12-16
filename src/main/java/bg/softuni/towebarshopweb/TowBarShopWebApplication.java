package bg.softuni.towebarshopweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TowBarShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TowBarShopWebApplication.class, args);
    }

}
