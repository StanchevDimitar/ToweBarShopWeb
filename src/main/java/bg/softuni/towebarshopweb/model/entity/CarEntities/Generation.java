package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "car_generations")
public class Generation extends BaseEntity{

    @Column
    private String name;
    @Column(name = "year")
    private String year;

    @ManyToOne
    private Model model;

    public Generation() {
    }

    public String getName() {
        return name;
    }

    public Generation setName(String name) {
        this.name = name;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Generation setYear(String year) {
        this.year = year;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Generation setModel(Model model) {
        this.model = model;
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
