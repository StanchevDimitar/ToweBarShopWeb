package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import bg.softuni.towebarshopweb.model.entity.TowBar;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @OneToOne
    private Make make;
    @OneToOne
    private Model model;
    @OneToOne
    private Generation generation;
    @Column
    private String year;
    @OneToOne
    private Serie serie;
    @OneToOne
    private Trim trim;
    @ManyToOne
    @JoinColumn(name = "body_id")
    private Body body;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private Set<TowBar> towBarList;

    public Car() {
    }

    public Body getBody() {
        return body;
    }

    public Car setBody(Body body) {
        this.body = body;
        return this;
    }

    public Set<TowBar> getTowBarList() {
        return towBarList;
    }

    public Car setTowBarList(Set<TowBar> towBarList) {
        this.towBarList = towBarList;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Car setYear(String year) {
        this.year = year;
        return this;
    }

    public Make getMake() {
        return make;
    }

    public Car setMake(Make make) {
        this.make = make;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Car setModel(Model model) {
        this.model = model;
        return this;
    }

    public Generation getGeneration() {
        return generation;
    }

    public Car setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    public Serie getSerie() {
        return serie;
    }

    public Car setSerie(Serie serie) {
        this.serie = serie;
        return this;
    }

    public Trim getTrim() {
        return trim;
    }

    public Car setTrim(Trim trim) {
        this.trim = trim;
        return this;
    }
}