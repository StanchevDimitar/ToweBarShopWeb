package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {


    private final CartItemService cartItemService;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;

    public AddressService(CartItemService cartItemService, UserService userService, CartItemRepository cartItemRepository) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
    }

    public void clearCart() {

        List<CartItem> products = cartItemService.getProducts(userService.getCurrentlyLoggedInUser());
        cartItemRepository.deleteAll(products);
    }
}
