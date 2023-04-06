package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.UserRegisterDto;
import bg.softuni.towebarshopweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    private String bad_credentials(){
        return UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @ModelAttribute("userModel")
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        if (!model.containsAttribute("bad_credentials")){
            model.addAttribute("bad_credentials",false);
        }
        return "auth-login";
    }


    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("isValid")) {
            model.addAttribute("isValid", true);
        }
        if (!model.containsAttribute("passwordMatch")){
            model.addAttribute("passwordMatch",true);
        }
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:register";
        }

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes
                    .addFlashAttribute("passwordMatch", false);
            return "redirect:register";
        }

        boolean isUsed = userService.checkUsername(userRegisterDto.getUsername());

        if (isUsed) {
            redirectAttributes.addFlashAttribute("userRegisterDto",userRegisterDto);
            redirectAttributes.addFlashAttribute("isValid", false);
            return "redirect:register";
        }

        userService.registerUser(userRegisterDto);


        return "redirect:login";

    }


}
