package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.CarViewDto;
import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import bg.softuni.towebarshopweb.repository.CarRepositories.BodyRepository;
import bg.softuni.towebarshopweb.repository.CarRepositories.GenerationRepository;
import bg.softuni.towebarshopweb.repository.CarRepositories.MakeRepository;
import bg.softuni.towebarshopweb.repository.CarRepositories.ModelRepository;
import bg.softuni.towebarshopweb.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private static final String url = "https://car-api2.p.rapidapi.com/api/makes?direction=asc&sort=id";
    //8e5ab0c6-5a30-471d-ade1-003588e4133a
    private static final String xRapid = "0df5c41effd9c432ca0cc560153eb600";
    private static final String getxRapid = "car-api2.p.rapidapi.com";
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;
    private final GenerationRepository generationRepository;
    private final BodyRepository bodyRepository;

    public Object getAllMakes() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", xRapid);
        headers.set("X-RapidAPI-Host", getxRapid);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response.getBody();
    }

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository, RestTemplate restTemplate, ModelMapper modelMapper, MakeRepository makeRepository, ModelRepository modelRepository, GenerationRepository generationRepository, BodyRepository bodyRepository) {
        this.carRepository = carRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.generationRepository = generationRepository;
        this.bodyRepository = bodyRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Car createCar(CarViewDto car) {

//        Optional<Car> optionalCar = carRepository.findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId
//                (car.getMake().getId(), car.getModel().getId(), car.getGeneration().getId(), car.getSerie().getId(), car.getTrim().getId());
//        Car map;
//        if (optionalCar.isEmpty()) {
//            map = modelMapper.map(car, Car.class);
//            carRepository.save(map);
//        } else {
//            map = optionalCar.get();
//        }
        return getCarByParameters(car);
    }

    private Car getCarByParameters(CarViewDto car) {
        Optional<Generation> byNameAndYear = generationRepository.findByName(car.getGeneration());

        if (byNameAndYear.isPresent()) {
            Generation generation = byNameAndYear.get();
            Body body = findBodyOrCreateNew(car);
            Optional<Car> optionalCar = carRepository
                    .findByMakeIdAndModelIdAndGenerationIdAndYearAndBodyId
                            (generation.getModel().getMake().getId(), generation.getModel().getId(), generation.getId(), car.getYear(), body.getId());

            return optionalCar.orElseGet(() -> crateNewCarAndSaveIt(car));

        }
        Optional<Model> optionalModel = modelRepository.findByName(car.getModel());

        if (optionalModel.isPresent()){
            Generation generation = new Generation();
            generation.setModel(optionalModel.get());
            generation.setName(car.getGeneration());
            generation.setYear(car.getYear());
            generationRepository.save(generation);

            Body body = findBodyOrCreateNew(car);
            Optional<Car> optionalCar = carRepository
                    .findByMakeIdAndModelIdAndGenerationIdAndYearAndBodyId
                            (generation.getModel().getMake().getId(), generation.getModel().getId(), generation.getId(), generation.getYear(), body.getId());

            return optionalCar.orElseGet(() -> crateNewCarAndSaveIt(car));
        }

        Optional<Make> optionalMake = makeRepository.findByName(car.getMake());

        if (optionalMake.isPresent()){
            Model model = new Model();
            model.setMake(optionalMake.get());
            model.setName(car.getModel());
            modelRepository.save(model);

            Generation generation = new Generation();
            generation.setModel(model);
            generation.setName(car.getGeneration());
            generation.setYear(car.getYear());
            generationRepository.save(generation);

            Body body = findBodyOrCreateNew(car);
            Optional<Car> optionalCar = carRepository
                    .findByMakeIdAndModelIdAndGenerationIdAndYearAndBodyId
                            (generation.getModel().getMake().getId(), generation.getModel().getId(), generation.getId(), generation.getYear(), body.getId());

            return optionalCar.orElseGet(() -> crateNewCarAndSaveIt(car));
        }

        Make make = new Make();
        make.setName(car.getMake());
        makeRepository.save(make);

        Model model = new Model();
        model.setMake(make);
        model.setName(car.getModel());
        modelRepository.save(model);

        Generation generation = new Generation();
        generation.setModel(model);
        generation.setName(car.getGeneration());
        generation.setYear(car.getYear());
        generationRepository.save(generation);

        Body body = findBodyOrCreateNew(car);
        Optional<Car> optionalCar = carRepository
                .findByMakeIdAndModelIdAndGenerationIdAndYearAndBodyId
                        (generation.getModel().getMake().getId(), generation.getModel().getId(), generation.getId(), generation.getYear(), body.getId());

        return optionalCar.orElseGet(() -> crateNewCarAndSaveIt(car));
    }

    private Car crateNewCarAndSaveIt(CarViewDto car) {
        Car newCar = new Car();

        newCar.setMake(makeRepository.findByName(car.getMake()).get());
        newCar.setModel(modelRepository.findByName(car.getModel()).get());
        newCar.setGeneration(generationRepository.findByName(car.getGeneration()).get());
        newCar.setBody(bodyRepository.findByName(car.getBody()).get());
        newCar.setYear(car.getYear());
        carRepository.save(newCar);

        return newCar;
    }

    private Body findBodyOrCreateNew(CarViewDto car) {
        Optional<Body> optBody = bodyRepository.findByName(car.getBody());
        if (optBody.isEmpty()) {
            Body body = new Body();
            body.setName(car.getBody());
            bodyRepository.save(body);
            return body;
        }

        return optBody.get();
    }


}
