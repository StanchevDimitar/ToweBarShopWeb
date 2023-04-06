package bg.softuni.towebarshopweb.model.entity;


public class TestCar {

    String model;

    @Override
    public String  toString() {
        return "TestCar{" +
                "model='" + model + '\'' +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
