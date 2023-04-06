package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.entity.CarEntities.Car;
import bg.softuni.towebarshopweb.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ShopPageController {

    private final CarRepository carRepository;
    private Long carId;

    public ShopPageController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @ModelAttribute("car")
    public CarDTO car(){
        return new CarDTO();
    }

    @GetMapping("/shop/{id}")
    public String getShopping(@PathVariable Long id) throws IllegalAccessException {
        this.carId = id;
        if (id != -1){
            Optional<Car> byId = carRepository.findById(id);
            if (byId.isEmpty()){
                throw new IllegalAccessException();
            }
            Car car = byId.get();

        }else {

        }

        return "shop-page";
    }
}
