package bg.softuni.towebarshopweb.model.dto;

import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

public class CarDTO {

    private Long id;
    @NotNull
    private Make make;
    @NotNull
    private Model model;
    @NotNull
    private Generation generation;
    @NotNull
    private Serie serie;
    @NotNull
    private Trim trim;

    public CarDTO() {
    }

    public Long getId() {
        return id;
    }

    public CarDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Make getMake() {
        return make;
    }

    public CarDTO setMake(Make make) {
        this.make = make;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public CarDTO setModel(Model model) {
        this.model = model;
        return this;
    }

    public Generation getGeneration() {
        return generation;
    }

    public CarDTO setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    public Serie getSerie() {
        return serie;
    }

    public CarDTO setSerie(Serie serie) {
        this.serie = serie;
        return this;
    }

    public Trim getTrim() {
        return trim;
    }

    public CarDTO setTrim(Trim trim) {
        this.trim = trim;
        return this;
    }


    @Override
    public String toString() {
        return "CarDTO{" +
                "make=" + make +
                ", model=" + model +
                ", generation=" + generation +
                ", serie=" + serie +
                ", trim=" + trim +
                '}';
    }
}


