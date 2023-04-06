package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_trims")
public class Trim extends BaseEntity {

    @Column
    private String name;
    @ManyToOne
    private Serie serie;
    @ManyToOne
    private Model model;

    public Trim() {
    }

    public String getName() {
        return name;
    }

    public Trim setName(String name) {
        this.name = name;
        return this;
    }

    public Serie getSerie() {
        return serie;
    }

    public Trim setSerie(Serie serie) {
        this.serie = serie;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Trim setModel(Model model) {
        this.model = model;
        return this;
    }
}
