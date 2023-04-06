package bg.softuni.towebarshopweb.model.dto;

import jakarta.validation.constraints.Size;


public class PhoneDto {

    @Size(min = 10,max = 10)
    private String number;

    public PhoneDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
