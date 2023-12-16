package bg.softuni.towebarshopweb.model.entity;

import bg.softuni.towebarshopweb.model.entity.CarEntities.NewCar;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tow_bars")
public class TowBar extends BaseEntity {


    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private TowBarType type;

    @Column
    private Integer quantity;

    @ManyToOne()
    private NewCar newCar;

    private BigDecimal price;


    public Integer getQuantity() {
        return quantity;
    }

    public TowBar setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TowBar(String name, TowBarType type) {
        this.name = name;
        this.type = type;
    }

    public TowBar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TowBarType getType() {
        return type;
    }

    public void setType(TowBarType type) {
        this.type = type;
    }

    public NewCar getCar() {
        return newCar;
    }

    public void setCar(NewCar newCar) {
        this.newCar = newCar;
    }

}
