package bg.softuni.towebarshopweb.service;
import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import bg.softuni.towebarshopweb.repository.TowBarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserService userService;

    @Mock
    private TowBarRepository towBarRepository;

    private CartItemService cartItemService;

    @BeforeEach
    public void setUp() {
        cartItemService = new CartItemService(cartItemRepository, userService, towBarRepository);
    }

    @Test
    public void testGetProducts() {
        // Arrange
        UserEntity user = new UserEntity();
        List<CartItem> expectedCartItems = new ArrayList<>();
        expectedCartItems.add(new CartItem());

        when(cartItemRepository.findAllByUser(user)).thenReturn(Optional.of(expectedCartItems));

        // Act
        List<CartItem> actualCartItems = cartItemService.getProducts(user);

        // Assert
        assertEquals(expectedCartItems, actualCartItems);
    }

    @Test
    public void testRemoveOffer() {
        // Arrange
        Long id = 1L;

        // Act
        cartItemService.removeOffer(id);

        // Assert
        verify(cartItemRepository).deleteById(id);
    }

    @Test
    public void testGetTotalPrice() {
        // Arrange
        BigDecimal expectedTotal = new BigDecimal(30);
        List<CartItem> cartItems = new ArrayList<>();
        TowBar towBar1 = new TowBar();
        towBar1.setPrice(new BigDecimal(10));
        TowBar towBar2 = new TowBar();
        towBar2.setPrice(new BigDecimal(20));
        cartItems.add(new CartItem(towBar1));
        cartItems.add(new CartItem(towBar2));

        // Act
        BigDecimal actualTotal = cartItemService.getTotalPrice(cartItems);

        // Assert
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void testAddProduct() {
        // Arrange
        TowBar towBar = new TowBar();

        when(userService.getCurrentlyLoggedInUser()).thenReturn(new UserEntity());

        // Act
        cartItemService.addProduct(towBar);

        // Assert
        verify(cartItemRepository).save(Mockito.any(CartItem.class));
    }

    @Test
    public void testUpdateQuantities() {
        // Set up mock data
        UserEntity user = new UserEntity();
        List<CartItem> cartItems = new ArrayList<>();
        TowBar towBar1 = new TowBar();
        towBar1.setId(1L);
        towBar1.setName("Tow Bar 1");
        towBar1.setQuantity(2);
        towBar1.setPrice(BigDecimal.valueOf(100));
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(1L);
        cartItem1.setUser(user);
        cartItem1.setTowBar(towBar1);
        cartItems.add(cartItem1);
        when(userService.getCurrentlyLoggedInUser()).thenReturn(user);
        when(cartItemRepository.findAllByUser(user)).thenReturn(Optional.of(cartItems));
        when(towBarRepository.save(any(TowBar.class))).thenReturn(towBar1);

        // Call the method to be tested
        List<TowBar> updatedTowBars = cartItemService.updateQuantities();

        // Verify the result
        assertEquals(1, updatedTowBars.size());
        assertEquals(1L, updatedTowBars.get(0).getId());
        assertEquals(1, updatedTowBars.get(0).getQuantity());
        verify(towBarRepository, times(1)).save(any(TowBar.class));
    }
}