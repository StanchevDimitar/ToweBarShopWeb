package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.DebitCardDto;
import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.model.entity.Order;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import bg.softuni.towebarshopweb.repository.OrderRepository;
import bg.softuni.towebarshopweb.service.CartItemService;
import bg.softuni.towebarshopweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller

public class CartController {


    private final UserService userService;
    private final CartItemService cartItemService;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;

    private List<CartItem> cartItems;

    public CartController(UserService userService, CartItemService cartItemService, OrderRepository orderRepository, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        cartItems = new ArrayList<>();
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @ModelAttribute
    public DebitCardDto cardDto(){
        return new DebitCardDto();
    }

    @GetMapping("/cart")
    public String getCart(Model model){

        UserEntity user = userService.getCurrentlyLoggedInUser();
        this.cartItems = cartItemService.getProducts(user);
        BigDecimal price = cartItemService.getTotalPrice(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("size",cartItems.size());
        model.addAttribute("price",price);
        model.addAttribute("pageTitle","Shopping Cart");

        return "cart";
    }

    @PostMapping("/cart")
    public String getCardInfo(@Valid DebitCardDto cardDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("cardDto", cardDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.cardDto", bindingResult);
            return "redirect:cart";
        }

        Order order = new Order();
        List<TowBar> towBarList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            towBarList.add(cartItem.getTowBar());
        }
        order.setItems(towBarList);
        order.setOrderPrice(cartItemService.getTotalPrice(cartItems));
        orderRepository.save(order);

        return "redirect:/address-info/" + order.getId();
    }

    @GetMapping("/cart/remove/{id}")
    public String removeOffer(@PathVariable Long id){

        cartItemService.removeOffer(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove")
    public String removeAll(){

        cartItemService.removeAllOffers();
        return "redirect:/cart";
    }
}
