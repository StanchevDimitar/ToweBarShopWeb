package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import bg.softuni.towebarshopweb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private CartItemService cartItemService;

    @Mock
    private UserService userService;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testClearCart() {
        // Mock currently logged in user
        UserEntity testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setUsername("testUser");
        Mockito.when(userService.getCurrentlyLoggedInUser()).thenReturn(testUser);

        // Mock cart items for the user
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(1L);
        cartItem1.setUser(testUser);
        CartItem cartItem2 = new CartItem();
        cartItem2.setId(2L);
        cartItem2.setUser(testUser);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        Mockito.when(cartItemService.getProducts(testUser)).thenReturn(cartItems);

        addressService.clearCart();

        Mockito.verify(cartItemRepository, Mockito.times(1)).deleteAll(cartItems);
    }
}
