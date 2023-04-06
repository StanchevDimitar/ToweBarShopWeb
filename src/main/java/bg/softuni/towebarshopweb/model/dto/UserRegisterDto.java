package bg.softuni.towebarshopweb.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @NotNull
    @Size(min = 2, max = 20, message ="First name must be between 2 and 20 symbols")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 20, message ="Last name must be between 2 and 20 symbols")
    private String lastName;
    @NotNull
    @Size(min = 5, max = 20, message ="Username must be between 5 and 20 symbols")
    private String username;

    @NotNull
    @Size(min = 5, max = 12, message ="Password must be between 5 and 12 symbols")
    private String password;
    @NotNull
    @Size(min = 5, max = 12, message ="Password must be between 5 and 12 symbols")
    private String confirmPassword;
    @Size(min = 2, max = 20, message ="Town must be between 2 and 20 symbols")
    private String town;
    @Email( message ="Invalid email")
    private String email;

    public UserRegisterDto() {
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
