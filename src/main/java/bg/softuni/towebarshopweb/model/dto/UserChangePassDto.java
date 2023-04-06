package bg.softuni.towebarshopweb.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserChangePassDto {

    @NotNull
    @Size(min = 5, max = 12, message = "Password must be between 5 and 12 symbols long!")
    private String currentPass;
    @NotNull
    @Size(min = 5, max = 12, message = "Password must be between 5 and 12 symbols long!")
    private String newPass;
    @NotNull
    @Size(min = 5, max = 12, message = "Password must be between 5 and 12 symbols long!")
    private String confirmNewPass;

    public UserChangePassDto() {
    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmNewPass() {
        return confirmNewPass;
    }

    public void setConfirmNewPass(String confirmNewPass) {
        this.confirmNewPass = confirmNewPass;
    }
}
