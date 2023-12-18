package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_makes")
public class Make extends BaseEntity {

    @Column
    private String name;


    public Make() {
    }

    public String getName() {
        return name;
    }

    public Make setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
