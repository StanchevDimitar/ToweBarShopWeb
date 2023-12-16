package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.dto.TowbarDto;
import bg.softuni.towebarshopweb.model.entity.CarEntities.NewCar;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import bg.softuni.towebarshopweb.repository.CarRepository;
import bg.softuni.towebarshopweb.repository.TowBarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TowBarService {
    private final ModelMapper modelMapper;
    private final TowBarRepository towBarRepository;
    private final CarRepository carRepository;
    private final CarService carService;

    public TowBarService(ModelMapper modelMapper, TowBarRepository towBarRepository, CarRepository carRepository, CarService carService) {
        this.modelMapper = modelMapper;
        this.towBarRepository = towBarRepository;
        this.carRepository = carRepository;
        this.carService = carService;
    }



    public TowBar findTowbarByCarAndType(CarDTO car, TowBarType towBarType) {

        NewCar map = carService.createCar(car);

        Optional<TowBar> byTypeAndCar = towBarRepository.findByTypeAndNewCar(towBarType, map);


        return byTypeAndCar.orElseGet(TowBar::new);

    }


    public List<TowBar> findAllByCar(CarDTO car) {

        List<TowBar> towBarList = new ArrayList<>();
        towBarList.add(findTowbarByCarAndType(car, TowBarType.FIXED));
        towBarList.add(findTowbarByCarAndType(car, TowBarType.DETACHABLE));
        towBarList.add(findTowbarByCarAndType(car, TowBarType.RETRACTABLE));
        return towBarList;
    }

    public void extractTowbarsView(Model model, TowBar fixed, TowBar detachable, TowBar retractable){
        if (!model.containsAttribute("fixed")) {
            model.addAttribute("fixed", fixed);
        }
        if (!model.containsAttribute("detachable")) {
            model.addAttribute("detachable", detachable);
        }
        if (!model.containsAttribute("retractable")) {
            model.addAttribute("retractable", retractable);
        }
    }


    public TowBar update(TowbarDto towbarDto, CarDTO carDTO, TowBarType type) {


        Optional<NewCar> optionalCar =
                carRepository
                        .findByMakeAndYearAndModelAndGenerationAndBody
                                ( carDTO.getMake(), carDTO.getYear(), carDTO.getModel(), carDTO.getGeneration(), carDTO.getBody());

        NewCar newCar = optionalCar.get();
        Optional<TowBar> byTypeAndCar = towBarRepository.findByTypeAndNewCar(type, newCar);
        TowBar towBar;
        if (byTypeAndCar.isPresent()) {
            towBar = byTypeAndCar.get();
            towBar.setPrice(towbarDto.getPrice());
            towBar.setQuantity(towbarDto.getQuantity());
        } else {
            towBar = modelMapper.map(towbarDto, TowBar.class);
            towBar.setCar(newCar);
            towBar.setType(type);
        }

        return towBarRepository.save(towBar);

    }
}
