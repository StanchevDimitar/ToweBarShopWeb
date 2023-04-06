package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    public CartItemService(CartItemRepository cartItemRepository, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
    }

    public List<CartItem> getProducts(UserEntity user){
        return cartItemRepository.findAllByUser(user).orElse(new ArrayList<>());

    }

    public void removeOffer(Long id) {
        cartItemRepository.deleteById(id);
    }

    public BigDecimal getTotalPrice(List<CartItem> cartItems) {
        BigDecimal total = new BigDecimal(0);
        for (CartItem item : cartItems) {
            BigDecimal price = item.getTowBar().getPrice();
            total = total.add(price);
        }

        return total;
    }

    public void removeAllOffers() {
        UserEntity curr = userService.getCurrentlyLoggedInUser();
        Optional<List<CartItem>> cartItems = cartItemRepository.findAllByUser(curr);
        cartItems.ifPresent(cartItemRepository::deleteAll);
    }
}
