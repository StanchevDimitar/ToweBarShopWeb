package bg.softuni.towebarshopweb.model.dto.carDto;

import bg.softuni.towebarshopweb.model.entity.CarEntities.Car;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class TowbarDto {


    private String name;

    private TowBarType type;

    @PositiveOrZero(message = "Value must be positive")
    private Integer quantity;
    @Positive
    private BigDecimal price;

    private Car car;

    public BigDecimal getPrice() {
        return price;
    }

    public TowbarDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TowbarDto() {
    }

    public String getName() {
        return name;
    }

    public TowbarDto setName(String name) {
        this.name = name;
        return this;
    }

    public TowBarType getType() {
        return type;
    }

    public TowbarDto setType(TowBarType type) {
        this.type = type;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public TowbarDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public TowbarDto setCar(Car car) {
        this.car = car;
        return this;
    }
}
