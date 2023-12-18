package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_body")
public class Body extends BaseEntity {

    @Column
    private String name;
    @OneToMany(mappedBy = "body", fetch = FetchType.EAGER)
    private List<Car> cars;

    public Body() {
    }

    public String getName() {
        return name;
    }

    public Body setName(String name) {
        this.name = name;
        return this;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Body setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
