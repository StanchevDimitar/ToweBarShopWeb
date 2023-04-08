package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.dto.UserRegisterDto;
import bg.softuni.towebarshopweb.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void registerConfirm_ShouldReturnRedirectToLogin_WhenRegistrationSuccessful() throws Exception {
        // Arrange
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername("testuser");
        userRegisterDto.setPassword("testpassword");
        userRegisterDto.setConfirmPassword("testpassword");
        userRegisterDto.setFirstName("ivan");
        userRegisterDto.setLastName("dragan");
        userRegisterDto.setTown("stoyan");
        userRegisterDto.setEmail("example@gmail.com");

        when(userService.checkUsername(userRegisterDto.getUsername())).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", userRegisterDto.getUsername())
                        .param("password", userRegisterDto.getPassword())
                        .param("ivan", userRegisterDto.getFirstName())
                        .param("dragan", userRegisterDto.getLastName())
                        .param("stoyan", userRegisterDto.getTown())
                        .param("example@gmail.com", userRegisterDto.getEmail())
                        .param("confirmPassword", userRegisterDto.getConfirmPassword()))

                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users/login"));
    }

    @Test
    public void registerConfirm_ShouldReturnRedirectToRegister_WhenValidationFails() throws Exception {
        // Arrange
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername("");
        userRegisterDto.setPassword("");
        userRegisterDto.setConfirmPassword("");

        // Act & Assert
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", userRegisterDto.getUsername())
                        .param("password", userRegisterDto.getPassword())
                        .param("confirmPassword", userRegisterDto.getConfirmPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("userModel"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.userModel"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("register"));
    }

    @Test
    public void registerConfirm_ShouldReturnRedirectToRegister_WhenUsernameAlreadyExists() throws Exception {
        // arrange
        String username = "testuser";
        String password = "testpassword";
        String email = "testemail@test.com";

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUsername(username);
        userRegisterDto.setPassword(password);
        userRegisterDto.setConfirmPassword(password);
        userRegisterDto.setEmail(email);

        userService.registerUser(userRegisterDto);

        // act
        MvcResult result = mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", username)
                        .param("password", password)
                        .param("confirmPassword", password)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        // assert
        String redirectedUrl = result.getResponse().getHeader("Location");
        assertTrue(redirectedUrl.endsWith("/users/register?isValid=true&passwordMatch=true"));
    }
}
