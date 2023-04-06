package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.entity.CarEntities.Car;
import bg.softuni.towebarshopweb.service.CarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HomeController {

    private final CarService carService;

    @ModelAttribute("car")
    public CarDTO car(){
        return new CarDTO();
    }

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String getHome(HttpSession session){

        Boolean inactiveUser = (Boolean) session.getAttribute("inactiveUser");
        if (inactiveUser != null && inactiveUser) {
            return "redirect:/login?inactive";
        }

        return "index";
    }

    @PostMapping("/find-order")
    public String findProduct(@Valid CarDTO carDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        Optional<Car> optionalCar = carService.findCar(carDTO);
        if (optionalCar.isPresent()){
            Long id = optionalCar.get().getId();
            return "redirect:/shop/" + id;//ToDo add id
        }


        return "redirect:/shop/" + -1;//ToDo add id
    }




}
