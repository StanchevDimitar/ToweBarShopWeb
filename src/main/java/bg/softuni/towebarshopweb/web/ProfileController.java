package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.FileUploadDto;
import bg.softuni.towebarshopweb.model.dto.UserChangePassDto;
import bg.softuni.towebarshopweb.model.dto.UserEditDto;
import bg.softuni.towebarshopweb.model.dto.UserMyProfileDto;
import bg.softuni.towebarshopweb.model.entity.FileEntity;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.repository.FileRepository;
import bg.softuni.towebarshopweb.service.FileService;
import bg.softuni.towebarshopweb.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/my-profile")
public class ProfileController {

    private static final String USERNAME_TAKEN = "Username is taken";
    private static final String EMAIL_TAKEN = "Email is already used";

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final FileRepository fileRepository;


    public ProfileController(UserService userService, ModelMapper modelMapper, FileRepository fileRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.fileRepository = fileRepository;
    }

    @ModelAttribute("userDto")
    public UserChangePassDto userChangePassDto() {
        return new UserChangePassDto();
    }

    @ModelAttribute
    public UserEditDto userEditDto() {
        return new UserEditDto();
    }


    @GetMapping
    private String getMyProf(Model model) {
        UserEntity currentlyLoggedInUser = userService.getCurrentlyLoggedInUser();

        UserMyProfileDto map = modelMapper.map(currentlyLoggedInUser, UserMyProfileDto.class);
        model.addAttribute("currentlyLoggedUser",map);
        return "my-profile";
    }


    @GetMapping("/change-password")
    private String getChangePass(Model model) {
        Long id = userService.getCurrentlyLoggedInUser().getId();
        Optional<FileEntity> byId = fileRepository.findById(id);
        if (byId.isPresent()){
            model.addAttribute("hasPhoto", true);
        }else {
            model.addAttribute("hasPhoto", false);
        }

        if (!model.containsAttribute("isCurrPasswordCorrect")) {
            model.addAttribute("isCurrPasswordCorrect", true);
        }
        if (!model.containsAttribute("areNotSame")) {
            model.addAttribute("areNotSame", true);
        }
        return "my-profile-changePassword";
    }

    @PostMapping("/change-password")
    private String changePass(@Valid UserChangePassDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDto", user);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userDto", bindingResult);
            return "redirect:change-password";
        }

        if (!user.getNewPass().equals(user.getConfirmNewPass())) {
            redirectAttributes.addFlashAttribute("areNotSame", false);
            return "redirect:change-password";
        }

        if (!userService.approveCurrentPassword(user.getCurrentPass())) {
            redirectAttributes.addFlashAttribute("isCurrPasswordCorrect", false);
            return "redirect:change-password";
        }



        userService.changePassword(user.getNewPass());


        return "redirect:/";
    }

    @GetMapping("/edit")
    private String getEdit(Model model) {
        UserEntity currentlyLoggedInUser = userService.getCurrentlyLoggedInUser();
        model.addAttribute("currentlyLoggedUser", currentlyLoggedInUser);
        if (!model.containsAttribute("usernameTaken")) {
            model.addAttribute("usernameTaken", false);
        }
        if (!model.containsAttribute("emailTaken")) {
            model.addAttribute("emailTaken", false);
        }


        return "my-profile-edit";
    }
    @GetMapping("/edit/photo")
    public HttpEntity<byte[]> download() {

        FileEntity fileDownloadModel = userService.getCurrentlyLoggedInUser().getImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MimeTypeUtils.parseMimeType(fileDownloadModel.getContentType())));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileDownloadModel.getFileName());
        headers.setContentLength(fileDownloadModel.getData().length);

        return new HttpEntity<>(fileDownloadModel.getData(), headers);
    }

    @PostMapping(value = "/edit")
    private String Edit(@Valid UserEditDto user, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes, FileUploadDto uploadDto) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditDto", user);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userEditDto", bindingResult);

            return "redirect:edit";
        }

        String hasError = userService.editUser(user,uploadDto.getImg());

        if (hasError.equals(USERNAME_TAKEN)) {
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:edit";
        }
        if (hasError.equals(EMAIL_TAKEN)) {
            redirectAttributes.addFlashAttribute("emailTaken", true);
            return "redirect:edit";
        }

        if (!user.getUsername().isBlank()){
            return "redirect:/users/logout";
        }

        return "redirect:/my-profile";
    }

    @GetMapping("/edit/delete")
    private String getDelete() {
        userService.disableUserById(userService.getCurrentlyLoggedInUser().getId());;
        //ToDo When the account is deactivated it should not be able to log in
        return "redirect:/";
    }
}
