package org.kirrif.model;

import java.util.Objects;

public class Engine {
    private String engine_type;
    private String engine_cylinders;
    private Integer engine_displacement;
    private Integer engine_horsepower;

    public Engine(){}

    public Engine(String engine_type,
                  String engine_cylinders,
                  Integer engine_displacement,
                  Integer engine_horsepower) {
        this.engine_type = engine_type;
        this.engine_cylinders = engine_cylinders;
        this.engine_displacement = engine_displacement;
        this.engine_horsepower = engine_horsepower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Objects.equals(engine_type, engine.engine_type) && Objects.equals(engine_cylinders, engine.engine_cylinders) && Objects.equals(engine_displacement, engine.engine_displacement) && Objects.equals(engine_horsepower, engine.engine_horsepower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine_type, engine_cylinders, engine_displacement, engine_horsepower);
    }

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
