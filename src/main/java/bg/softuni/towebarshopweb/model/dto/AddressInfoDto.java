package bg.softuni.towebarshopweb.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AddressInfoDto {

    @Size(min = 2, max = 10,message = "First name should be between 2 and 10 symbols")
    @NotNull
    private String firstName;
    @Size(min = 2, max = 10,message = "Last name should be between 2 and 10 symbols")
    private String lastName;
    @Size(min = 2, max = 10,message = "City must not be null")
    @NotNull
    private String city;
    @Positive(message = "Zip must be positive number")
    @NotNull
    private Integer zip;
    @Size(min = 2, max = 20, message = "Address must be between 2 and 20 symbols")
    @NotNull
    private String address;
    @Size(min = 10,max = 10, message = "Telephone number must be 10 digits exact")
    @NotNull
    private String telephoneNumber;

    public AddressInfoDto() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
