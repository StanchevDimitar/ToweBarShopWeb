package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.entity.CarEntities.Car;
import bg.softuni.towebarshopweb.repository.CarRepository;
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
    private static final String getxRapid =  "car-api2.p.rapidapi.com";
    private final RestTemplate restTemplate;

    public Object getAllMakes() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", xRapid);
        headers.set("X-RapidAPI-Host",getxRapid);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);
        return response.getBody();
    }

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository, RestTemplate restTemplate) {
        this.carRepository = carRepository;
        this.restTemplate = restTemplate;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> findCar(CarDTO car) {
        return carRepository.findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId
                (car.getMake().getId(), car.getModel().getId(), car.getGeneration().getId(), car.getSerie().getId(), car.getTrim().getId());
    }


}
