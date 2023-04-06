package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.UserBasicInfoView;
import bg.softuni.towebarshopweb.model.dto.Username;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import bg.softuni.towebarshopweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @ModelAttribute
    public Username username(){
        return new Username();
    }

    private final UserService userService;

    private UserBasicInfoView user;

    public AdminController( UserService userService) {

        this.userService = userService;

    }

    @GetMapping("/change-role")
    public String getRoleEdit(Model model){

        model.addAttribute("user",user);
        return "give-role";
    }

    @PostMapping("/change-role")
    public String findUser(Username username){

        this.user = userService.findByUsername(username.getUsername());

        return "redirect:change-role";
    }



    @GetMapping("/change-role/remove_admin/{id}")
    public String removeAdmin(@PathVariable Long id){
        RoleNameEnum role = RoleNameEnum.ADMIN;
        userService.removeRoleById(id,role);
        user.removeRole(role);
        return "redirect:/admin/change-role";
    }
    @GetMapping("/change-role/remove_moderator/{id}")
    public String removeModerator(@PathVariable Long id){
        RoleNameEnum role = RoleNameEnum.MODERATOR;
        userService.removeRoleById(id,role);
        user.removeRole(role);
        return "redirect:/admin/change-role";
    }
    @GetMapping("/change-role/add_moderator/{id}")
    public String addModerator(@PathVariable Long id){
        RoleNameEnum role = RoleNameEnum.MODERATOR;
        userService.addRoleToUserById(id,role);
        user.addRole(role);
        return "redirect:/admin/change-role";
    }
    @GetMapping("/change-role/add_admin/{id}")
    public String addAdmin(@PathVariable Long id){
        RoleNameEnum role = RoleNameEnum.ADMIN;
        userService.addRoleToUserById(id,role);
        user.addRole(role);
        return "redirect:/admin/change-role";
    }

    @GetMapping("/change-role/disable/{id}")
    public String disable(@PathVariable Long id){

        userService.disableUserById(id);
        user.setActive(false);
        return "redirect:/admin/change-role";
    }
    @GetMapping("/change-role/activate/{id}")
    public String enable(@PathVariable Long id){

        userService.enableUserById(id);
        user.setActive(true);
        return "redirect:/admin/change-role";
    }


    @GetMapping("/find-active")
    public String getActive(Model model){

        List<UserEntity> allActive = userService.findAllActive();
        model.addAttribute("allActive", allActive);
        model.addAttribute("size",allActive.size());

        return "find-all-active";
    }

    @GetMapping("/find-nonActive/enable/{id}")
    public String enableNonActive(@PathVariable Long id){

        userService.enableUserById(id);
        return "redirect:/admin/find-nonActive";
    }

    @GetMapping("/find-active/disable/{id}")
    public String disableActive(@PathVariable Long id){

        userService.disableUserById(id);
        return "redirect:/admin/find-active";
    }

    @GetMapping("/find-nonActive")
    public String getNoneActive(Model model){

        List<UserEntity> allNonActive = userService.findAllNonActive();
        model.addAttribute("allNonActive", allNonActive);
        model.addAttribute("size",allNonActive.size());


        return "find-all-non-active";
    }
}
