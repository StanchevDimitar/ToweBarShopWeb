package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.PhoneDto;
import bg.softuni.towebarshopweb.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class Quick_OrderController {

    private final PhoneService phoneService;

    public Quick_OrderController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @ModelAttribute("phone")
    public PhoneDto phone(){
        return new PhoneDto();
    }

    @GetMapping("/quick-order")
    public String getQuickOrder(Model model){
        if (!model.containsAttribute("entered")){
            model.addAttribute("entered",false);
        }
        return "quick-order";
    }

    @PostMapping("/quick-order")
    public String getPhones(@Valid PhoneDto phone, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("phone", phone);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:quick-order";
        }



        phoneService.add(phone.getNumber());
        redirectAttributes.addFlashAttribute("entered",true);
        return "redirect:quick-order";
    }
}
