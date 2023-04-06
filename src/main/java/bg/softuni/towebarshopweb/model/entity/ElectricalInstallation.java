package bg.softuni.towebarshopweb.model.entity;

import bg.softuni.towebarshopweb.model.enums.ElectricalSystemType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "electrical_installations")
public class ElectricalInstallation extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @NotNull
    private ElectricalSystemType type;

    @Column(nullable = false)
    private BigDecimal price;

    public ElectricalInstallation() {
    }

    public ElectricalSystemType getType() {
        return type;
    }

    public void setType(ElectricalSystemType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
