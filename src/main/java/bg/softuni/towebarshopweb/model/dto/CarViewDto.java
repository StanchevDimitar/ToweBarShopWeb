package bg.softuni.towebarshopweb.model.dto;

import jakarta.validation.constraints.NotNull;

public class CarViewDto {
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


    public CarViewDto() {
    }

    public Long getId() {
        return id;
    }

    public CarViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CarViewDto setMake(String make) {
        this.make = make;
        return this;
    }

    public String getYear() {
        return year;
    }

    public CarViewDto setYear(String year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarViewDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getGeneration() {
        return generation;
    }

    public CarViewDto setGeneration(String generation) {
        this.generation = generation;
        return this;
    }

    public String getBody() {
        return body;
    }

    public CarViewDto setBody(String body) {
        this.body = body;
        return this;
    }
}
