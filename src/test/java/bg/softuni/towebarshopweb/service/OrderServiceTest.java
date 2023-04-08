package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.AddressInfoDto;
import bg.softuni.towebarshopweb.model.entity.Order;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, modelMapper, userService);
    }

    @Test
    public void testUpdateOrderWithValidData() {
        // Arrange
        Long id = 1L;
        List<TowBar> towBarList = new ArrayList<>();
        TowBar towBar = new TowBar();
        towBar.setId(1L);
        towBarList.add(towBar);
        AddressInfoDto addressInfoDto = new AddressInfoDto();
        addressInfoDto.setAddress("Address");
        addressInfoDto.setCity("City");
        addressInfoDto.setZip(1000);
        addressInfoDto.setFirstName("FirstName");
        addressInfoDto.setLastName("LastName");
        addressInfoDto.setTelephoneNumber("0888888888");
        Order order = new Order();
        order.setId(id);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(userService.getCurrentlyLoggedInUser()).thenReturn(null);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        boolean result = orderService.updateOrder(id, addressInfoDto, towBarList);

        // Assert
        assertTrue(result);
        assertEquals(addressInfoDto.getAddress(), order.getAddress());
        assertEquals(addressInfoDto.getCity(), order.getCity());
        assertEquals(addressInfoDto.getZip(), order.getZip());
        assertEquals(addressInfoDto.getFirstName(), order.getFirstName());
        assertEquals(addressInfoDto.getLastName(), order.getLastName());
        assertEquals(addressInfoDto.getTelephoneNumber(), order.getTelephoneNumber());
        assertEquals(towBarList, order.getProducts());
        assertTrue(order.getFinished());
        assertNotNull(order.getCompletedTime());
        assertNull(order.getUser());
        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, times(1)).save(order);
        verifyNoMoreInteractions(orderRepository);
        verify(userService, times(1)).getCurrentlyLoggedInUser();
        verifyNoMoreInteractions(userService);
        verifyNoInteractions(modelMapper);
    }

    @Test
    public void testUpdateOrderWithInvalidOrderId() {
        // Arrange
        Long invalidId = 1234L;
        AddressInfoDto addressInfoDto = new AddressInfoDto();
        List<TowBar> towBarList = new ArrayList<>();

        // Mock the repository to return an empty optional
        when(orderRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Act
        boolean result = orderService.updateOrder(invalidId, addressInfoDto, towBarList);

        // Assert
        assertFalse(result); // The method should return false when given an invalid ID

        // Verify that findById() was called with the correct ID
        verify(orderRepository).findById(invalidId);

        // Verify that no other interactions with the repository occurred
        verifyNoMoreInteractions(orderRepository);
    }
}