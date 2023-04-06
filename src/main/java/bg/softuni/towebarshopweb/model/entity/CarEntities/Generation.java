package bg.softuni.towebarshopweb.model.entity.CarEntities;

import bg.softuni.towebarshopweb.model.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "car_generations")
public class Generation extends BaseEntity{

    @Column
    private String name;
    @Column(name = "year_begin")
    private Integer yearBegin;
    @Column(name = "year_end")
    private Integer yearEnd;

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

    public Integer getYearBegin() {
        return yearBegin;
    }

    public Generation setYearBegin(Integer yearBegin) {
        this.yearBegin = yearBegin;
        return this;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public Generation setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Generation setModel(Model model) {
        this.model = model;
        return this;
    }
}
