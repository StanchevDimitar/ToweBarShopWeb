package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.CarViewDto;
import bg.softuni.towebarshopweb.model.dto.TowbarDto;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import bg.softuni.towebarshopweb.repository.CarRepository;
import bg.softuni.towebarshopweb.repository.TowBarRepository;
import bg.softuni.towebarshopweb.service.TowBarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddProductController {

    private final TowBarRepository towBarRepository;
    private final CarRepository carRepository;
    private final TowBarService towBarService;
    private CarViewDto car;
    private TowBar fixed;
    private TowBar detachable;
    private TowBar retractable;

    public AddProductController(TowBarRepository towBarRepository, CarRepository carRepository, TowBarService towBarService) {
        this.towBarRepository = towBarRepository;
        this.carRepository = carRepository;
        this.towBarService = towBarService;
    }

    @ModelAttribute
    public CarViewDto car() {
        return new CarViewDto();
    }

    @GetMapping("/add-product")
    public String getAddProduct(Model model) {
        if (!model.containsAttribute("car")) {
            model.addAttribute("car", this.car);
        }
        towBarService.extractTowbarsView(model, fixed, detachable, retractable);

        return "add-products";
    }

    @PostMapping("/add-product")
    public String addingProduct(@Valid CarViewDto car, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:add-product";
        }
        this.car = car;


        List<TowBar> allByCar = towBarService.findAllByCar(car);


        this.fixed = allByCar.get(0);
        this.detachable = allByCar.get(1);
        this.retractable = allByCar.get(2);

        return "redirect:add-product";
    }

    @PostMapping("/add-product/add/{type}")
    public String updateFixed(@Valid TowbarDto towbarDto, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, @PathVariable TowBarType type) {

        if (bindingResult.hasErrors()) {
            return "redirect:/add-product";
        }

        TowBar update = towBarService.update(towbarDto, car, type);
        switch (type) {
            case FIXED -> this.fixed = update;
            case DETACHABLE -> this.detachable = update;
            case RETRACTABLE -> this.retractable = update;
        }

        return "redirect:/add-product";
    }

}
