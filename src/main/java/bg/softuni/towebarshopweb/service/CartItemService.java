package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import bg.softuni.towebarshopweb.repository.TowBarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final TowBarRepository towBarRepository;

    public CartItemService(CartItemRepository cartItemRepository, UserService userService, TowBarRepository towBarRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.towBarRepository = towBarRepository;
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

    public void addProduct(TowBar fixed) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(userService.getCurrentlyLoggedInUser());
        cartItem.setTowBar(fixed);
        cartItemRepository.save(cartItem);
    }

    public List<TowBar> updateQuantities() {
        List<TowBar> towbars = new ArrayList<>();
        List<CartItem> cartItems = cartItemRepository.findAllByUser(userService.getCurrentlyLoggedInUser()).get();
        for (CartItem item : cartItems) {
            TowBar towBar = item.getTowBar();
            towBar.setQuantity(item.getTowBar().getQuantity() -1);
            towbars.add(item.getTowBar());
            towBarRepository.save(towBar);
        }
        return towbars;
    }
}
