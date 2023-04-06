package bg.softuni.towebarshopweb.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(HttpSession session){

        Boolean inactiveUser = (Boolean) session.getAttribute("inactiveUser");
        if (inactiveUser != null && inactiveUser) {
            return "redirect:/login?inactive";
        }

        return "index";
    }




}
