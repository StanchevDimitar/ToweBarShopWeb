package bg.softuni.towebarshopweb.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UserEditDto {


    @Pattern(regexp = "^$|^.{5,10}$", message ="Username must be between 5 and 20 symbols")
    private String username;
    @Pattern(regexp = "^$|^.{2,10}$", message ="First name must be between 2 and 20 symbols")
    private String firstName;
    @Pattern(regexp = "^$|^.{2,10}$", message ="Last name must be between 2 and 20 symbols")
    private String lastName;

    @Pattern(regexp = "^$|^.{2,10}$", message ="Town must be between 2 and 20 symbols")
    private String town;

    @Email(message ="Email must be valid")
    private String email;

    public UserEditDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
