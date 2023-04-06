package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.dto.carDto.TowbarDto;
import bg.softuni.towebarshopweb.model.entity.CarEntities.Car;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import bg.softuni.towebarshopweb.repository.CarRepository;
import bg.softuni.towebarshopweb.repository.TowBarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        Optional<Car> optionalCar = carRepository.findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId
                (car.getMake().getId(), car.getModel().getId(), car.getGeneration().getId(), car.getSerie().getId(), car.getTrim().getId());
        Car map;
        if (optionalCar.isEmpty()){
            map = modelMapper.map(car, Car.class);
            carRepository.save(map);
        }else {
            map = optionalCar.get();
        }
        Optional<TowBar> byTypeAndCar = towBarRepository.findByTypeAndCar(towBarType, map);


        if (byTypeAndCar.isPresent()) {
            return byTypeAndCar.get();
        }
        return new TowBar();

    }

    public List<TowBar> findAllByCar(CarDTO car) {

        List<TowBar> towBarList = new ArrayList<>();
        towBarList.add(findTowbarByCarAndType(car, TowBarType.FIXED));
        towBarList.add(findTowbarByCarAndType(car, TowBarType.DETACHABLE));
        towBarList.add(findTowbarByCarAndType(car, TowBarType.RETRACTABLE));
        return towBarList;
    }

    public TowBar update(TowbarDto towbarDto, CarDTO carDTO, TowBarType type) {



        Optional<Car> optionalCar = carRepository.findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId
                (carDTO.getMake().getId(), carDTO.getModel().getId(), carDTO.getGeneration().getId(), carDTO.getSerie().getId(), carDTO.getTrim().getId());

        Car car = optionalCar.get();
        Optional<TowBar> byTypeAndCar = towBarRepository.findByTypeAndCar(type, car);
        TowBar towBar;
        if (byTypeAndCar.isPresent()){
            towBar = byTypeAndCar.get();
            towBar.setPrice(towbarDto.getPrice());
            towBar.setQuantity(towbarDto.getQuantity());
        }else {
            towBar = modelMapper.map(towbarDto, TowBar.class);
            towBar.setCar(car);
            towBar.setType(type);
        }

        return towBarRepository.save(towBar);

    }
}
