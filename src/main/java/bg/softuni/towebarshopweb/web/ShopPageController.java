package bg.softuni.towebarshopweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopPageController {

    private Long carId;


    @GetMapping("/shop/{id}")
    public String getShopping(@PathVariable Long id) {
        this.carId = id;
        return "shop-page";
    }
}
