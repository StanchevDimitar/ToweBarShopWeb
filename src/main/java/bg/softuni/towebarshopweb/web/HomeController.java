package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.CarViewDto;
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


@Controller
public class HomeController {

    private final CarService carService;

    @ModelAttribute("car")
    public CarViewDto car(){
        //ToDo new carDto for the changes
        return new CarViewDto();
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
    public String findProduct(@Valid CarViewDto carDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        Car car = carService.createCar(carDTO);
        Long id = car.getId();


        return "redirect:/shop/" + id;//ToDo add id
    }




}
