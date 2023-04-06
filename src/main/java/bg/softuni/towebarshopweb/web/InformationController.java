package bg.softuni.towebarshopweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InformationController {


    @GetMapping("/fixed")
    public String getFixed(){
        return "fixed-info";
    }

    @GetMapping("/detachable")
    public String getDetachable(){
        return "detachable-info";
    }
    @GetMapping("/retractable")
    public String getRetractable(){
        return "retractable-info";
    }

}
