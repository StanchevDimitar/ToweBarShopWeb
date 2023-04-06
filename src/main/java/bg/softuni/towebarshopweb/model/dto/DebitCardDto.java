package bg.softuni.towebarshopweb.model.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.YearMonth;

public class DebitCardDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "Cardholder name must ber between 3 and 20 symbols")
    private String cardholderName;

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotEmpty
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "Expiry date must be in the format MM/YY")
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

    public DebitCardDto() {
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public DebitCardDto setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public DebitCardDto setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public DebitCardDto setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public DebitCardDto setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
}
