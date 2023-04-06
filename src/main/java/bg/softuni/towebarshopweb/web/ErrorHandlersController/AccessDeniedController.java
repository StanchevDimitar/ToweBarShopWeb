package bg.softuni.towebarshopweb.web.ErrorHandlersController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/ErrorPages/403";
    }
}
