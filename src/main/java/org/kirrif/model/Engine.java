package org.kirrif.model;

public class Engine {
    private String engine_type;
    private String engine_cylinders;
    private Integer engine_displacement;
    private Integer engine_horsepower;

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public String getEngine_cylinders() {
        return engine_cylinders;
    }

    public void setEngine_cylinders(String engine_cylinders) {
        this.engine_cylinders = engine_cylinders;
    }

    public Integer getEngine_displacement() {
        return engine_displacement;
    }

    public void setEngine_displacement(Integer engine_displacement) {
        this.engine_displacement = engine_displacement;
    }

    public Integer getEngine_horsepower() {
        return engine_horsepower;
    }

    public void setEngine_horsepower(Integer engine_horsepower) {
        this.engine_horsepower = engine_horsepower;
    }
}
