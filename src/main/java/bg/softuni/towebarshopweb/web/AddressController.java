package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.AddressInfoDto;
import bg.softuni.towebarshopweb.model.entity.CartItem;
import bg.softuni.towebarshopweb.repository.CartItemRepository;
import bg.softuni.towebarshopweb.service.AddressService;
import bg.softuni.towebarshopweb.service.CartItemService;
import bg.softuni.towebarshopweb.service.OrderService;
import bg.softuni.towebarshopweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddressController {

    private final OrderService orderService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final AddressService addressService;

    private Long saveId;

    public AddressController(OrderService orderService, CartItemService cartItemService, UserService userService, UserService userService1, CartItemRepository cartItemRepository, AddressService addressService) {
        this.orderService = orderService;
        this.cartItemService = cartItemService;
        this.userService = userService1;
        this.cartItemRepository = cartItemRepository;
        this.addressService = addressService;
    }

    @ModelAttribute
    public AddressInfoDto addressInfoDto(){
        return new AddressInfoDto();
    }

    @GetMapping("/address-info/{id}")
    public String getAddress(@PathVariable Long id){
        this.saveId = id;
        return "address-info";
    }

    @PostMapping("/address-info")
    public String logAddress(@Valid AddressInfoDto addressInfoDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addressInfoDto", addressInfoDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addressInfoDto",
                    bindingResult);

            return "redirect:/address-info/"+ this.saveId;
        }

        orderService.updateOrder(this.saveId, addressInfoDto);

        addressService.clearCart();

        return "redirect:complete-order";
    }

    @GetMapping("/complete-order")
    private String getComplete(){
        return "complete-order";
    }
}
