package bg.softuni.towebarshopweb.service;
import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import bg.softuni.towebarshopweb.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CarService carService;

    private CarDTO carDTO;
    private Car car;

    @BeforeEach
    public void setUp() {
        carDTO = new CarDTO();
        carDTO.setMake(new Make());
        carDTO.setModel(new Model());
        carDTO.setGeneration(new Generation());
        carDTO.setSerie(new Serie());
        carDTO.setTrim(new Trim());

        car = new Car();
        car.setMake(new Make());
        car.setModel(new Model());
        car.setGeneration(new Generation());
        car.setSerie(new Serie());
        car.setTrim(new Trim());
    }

    @Test
    public void testSaveCar() {
        when(carRepository.save(any(Car.class))).thenReturn(car);
        Car savedCar = carService.save(car);
        assertThat(savedCar).isNotNull();
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testGetAllCars() {
        List<Car> cars = List.of(car);
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> allCars = carService.getAllCars();
        assertThat(allCars).isEqualTo(cars);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testCreateCarWithNewCar() {
        when(modelMapper.map(carDTO, Car.class)).thenReturn(car);
        when(carRepository.findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId(any(), any(), any(), any(), any())).thenReturn(Optional.empty());
        when(carRepository.save(car)).thenReturn(car);
        Car createdCar = carService.createCar(carDTO);
        assertThat(createdCar).isEqualTo(car);
        verify(modelMapper, times(1)).map(carDTO, Car.class);
        verify(carRepository, times(1)).findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId(any(), any(), any(), any(), any());
        verify(carRepository, times(1)).save(car);
    }




    @Test
    public void testGetAllMakes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0df5c41effd9c432ca0cc560153eb600");
        headers.set("X-RapidAPI-Host", "car-api2.p.rapidapi.com");

        ResponseEntity<String> response = new ResponseEntity<>("test", HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class))).thenReturn(response);

        Object result = carService.getAllMakes();

        assertEquals("test", result);
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
    }
}
