package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.AddressInfoDto;
import bg.softuni.towebarshopweb.model.entity.Order;
import bg.softuni.towebarshopweb.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public boolean updateOrder(Long id, AddressInfoDto addressInfoDto) {

        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()){

            Order order = byId.get();
            order.setCity(addressInfoDto.getCity());
            order.setAddress(addressInfoDto.getAddress());
            order.setZip(addressInfoDto.getZip());
            order.setFirstName(addressInfoDto.getFirstName());
            order.setLastName(addressInfoDto.getLastName());
            order.setTelephoneNumber(addressInfoDto.getTelephoneNumber());

            order.setFinished(true);
            order.setCompletedTime(LocalDateTime.now());
            order.setUser(userService.getCurrentlyLoggedInUser());
            orderRepository.save(order);
            return true;
        }

        return false;
    }
}
