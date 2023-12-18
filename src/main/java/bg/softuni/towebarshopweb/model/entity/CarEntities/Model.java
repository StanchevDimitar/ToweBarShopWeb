package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_models")
public class Model extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    private Make make;

    public Model() {
    }

    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public Make getMake() {
        return make;
    }

    public Model setMake(Make make) {
        this.make = make;
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
