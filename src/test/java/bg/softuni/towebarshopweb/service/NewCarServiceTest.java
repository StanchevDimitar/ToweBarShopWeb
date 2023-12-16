package bg.softuni.towebarshopweb.service;
import bg.softuni.towebarshopweb.model.dto.CarDTO;
import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import bg.softuni.towebarshopweb.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewCarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CarService carService;

    private CarDTO carDTO;
    private NewCar newCar;



    @Test
    public void testSaveCar() {
        when(carRepository.save(any(NewCar.class))).thenReturn(newCar);
        NewCar savedNewCar = carService.save(newCar);
        assertThat(savedNewCar).isNotNull();
        verify(carRepository, times(1)).save(newCar);
    }

    @Test
    public void testGetAllCars() {
        List<NewCar> newCars = List.of(newCar);
        when(carRepository.findAll()).thenReturn(newCars);
        List<NewCar> allNewCars = carService.getAllCars();
        assertThat(allNewCars).isEqualTo(newCars);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testCreateCarWithNewCar() {
        when(modelMapper.map(carDTO, NewCar.class)).thenReturn(newCar);
        when(carRepository.findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId(any(), any(), any(), any(), any())).thenReturn(Optional.empty());
        when(carRepository.save(newCar)).thenReturn(newCar);
        NewCar createdNewCar = carService.createCar(carDTO);
        assertThat(createdNewCar).isEqualTo(newCar);
        verify(modelMapper, times(1)).map(carDTO, NewCar.class);
        verify(carRepository, times(1)).findByMakeIdAndModelIdAndGenerationIdAndSerieIdAndTrimId(any(), any(), any(), any(), any());
        verify(carRepository, times(1)).save(newCar);
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
