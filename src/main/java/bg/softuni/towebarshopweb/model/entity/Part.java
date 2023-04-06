package bg.softuni.towebarshopweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private Integer thickness;
    @Column
    private String dxf;
    @Column
    private String dwg;
    @Column
    private String pdf;


    public Part(String name, Integer thickness) {
        this.name = name;
        this.thickness = thickness;
    }

    public Part() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public String getDxf() {
        return dxf;
    }

    public void setDxf(String dxf) {
        this.dxf = dxf;
    }

    public String getDwg() {
        return dwg;
    }

    public void setDwg(String dwg) {
        this.dwg = dwg;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
