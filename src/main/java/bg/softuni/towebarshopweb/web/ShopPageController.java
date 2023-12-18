package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.CarViewDto;
import bg.softuni.towebarshopweb.model.entity.CarEntities.Car;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import bg.softuni.towebarshopweb.repository.CarRepository;
import bg.softuni.towebarshopweb.service.CartItemService;
import bg.softuni.towebarshopweb.service.TowBarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopPageController {

    private final CarRepository carRepository;
    private final TowBarService towBarService;
    private final ModelMapper modelMapper;
    private final CartItemService cartItemService;

    private TowBar fixed;
    private TowBar retractable;
    private TowBar detachable;
    private Long currId;


    public ShopPageController(CarRepository carRepository, TowBarService towBarService, ModelMapper modelMapper, CartItemService cartItemService) {
        this.carRepository = carRepository;
        this.towBarService = towBarService;
        this.modelMapper = modelMapper;
        this.cartItemService = cartItemService;
    }

    @ModelAttribute("car")
    public CarViewDto car(){
        return new CarViewDto();
    }


    @GetMapping("/shop/{id}")
    public String getShopping(@PathVariable Long id, Model model) throws IllegalAccessException {

        currId = id;

        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()){
            throw new IllegalAccessException();
        }
        CarViewDto carDto = modelMapper.map(byId.get(), CarViewDto.class);
        List<TowBar> allByCar = towBarService.findAllByCar(carDto);


        this.fixed = allByCar.get(0);
        this.detachable = allByCar.get(1);
        this.retractable = allByCar.get(2);

        if (model.containsAttribute("car")) {
            model.addAttribute("car",carDto);
        }

//        towBarService.extractTowbarsView(model,fixed,detachable,retractable);

        if (!model.containsAttribute("fixed")) {
            model.addAttribute("fixed", fixed);
        }
        if (!model.containsAttribute("detachable")) {
            model.addAttribute("detachable", detachable);
        }
        if (!model.containsAttribute("retractable")) {
            model.addAttribute("retractable", retractable);
        }

        return "shop-page";
    }

    @GetMapping("/shop/add-to-cart/{type}")
    public String addToCart(@PathVariable TowBarType type){

        switch (type){
            case FIXED ->cartItemService.addProduct(this.fixed);
            case RETRACTABLE -> cartItemService.addProduct(this.retractable);
            case DETACHABLE -> cartItemService.addProduct(this.detachable);
        }



        return "redirect:/shop/" + currId;
    }


}
