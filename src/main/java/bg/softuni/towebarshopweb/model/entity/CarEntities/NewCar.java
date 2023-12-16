package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "new_car")
public class NewCar extends BaseEntity {

    @NotNull
    private String make;
    @NotNull
    private String year;
    @NotNull
    private String model;
    @NotNull
    private String generation;
    @NotNull
    private String body;

    public NewCar() {
    }

    public String getMake() {
        return make;
    }

    public NewCar setMake(String make) {
        this.make = make;
        return this;
    }

    public String getYear() {
        return year;
    }

    public NewCar setYear(String year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public NewCar setModel(String model) {
        this.model = model;
        return this;
    }

    public String getGeneration() {
        return generation;
    }

    public NewCar setGeneration(String generation) {
        this.generation = generation;
        return this;
    }

    public String getBody() {
        return body;
    }

    public NewCar setBody(String body) {
        this.body = body;
        return this;
    }
}
