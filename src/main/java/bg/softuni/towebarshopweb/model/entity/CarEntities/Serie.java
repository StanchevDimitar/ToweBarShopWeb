package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_series")
public class Serie extends BaseEntity {
    @Column
    private String name;

    public Serie() {
    }

    public String getName() {
        return name;
    }

    public Serie setName(String name) {
        this.name = name;
        return this;
    }


}
