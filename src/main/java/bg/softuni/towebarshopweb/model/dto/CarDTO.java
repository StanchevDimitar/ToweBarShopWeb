package bg.softuni.towebarshopweb.model.dto;

import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

public class CarDTO {

    private Long id;
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


    public CarDTO() {
    }

    public Long getId() {
        return id;
    }

    public CarDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CarDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getYear() {
        return year;
    }

    public CarDTO setYear(String year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public String getGeneration() {
        return generation;
    }

    public CarDTO setGeneration(String generation) {
        this.generation = generation;
        return this;
    }

    public String getBody() {
        return body;
    }

    public CarDTO setBody(String body) {
        this.body = body;
        return this;
    }
}


