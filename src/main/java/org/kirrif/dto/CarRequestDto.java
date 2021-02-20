package org.kirrif.dto;

import org.kirrif.model.Body;
import org.kirrif.model.Car;
import org.kirrif.model.Engine;

import java.util.Objects;

public class CarRequestDto {

    private Long id;
    private String segment;
    private Long brand_id;
    private String model;
    private String generation;
    private String modification;
    private String year_range;
    private String engine_type;
    private String engine_cylinders;
    private Integer engine_displacement;
    private Integer engine_horsepower;
    private String gearbox;
    private String wheel_drive;
    private Integer body_length;
    private Integer body_width;
    private Integer body_height;
    private String body_style;
    private Double acceleration;
    private Integer max_speed;


    public Car toCar(){
        Car car = new Car();
        Body body = new Body();
        Engine engine = new Engine();
        car.setId(id);
        car.setSegment(segment);
        car.setBrand_id(brand_id);
        car.setModel(model);
        car.setGeneration(generation);
        car.setModification(modification);
        car.setYear_range(year_range);
        car.setGearbox(gearbox);
        car.setWheel_drive(wheel_drive);
        car.setAcceleration(acceleration);
        car.setMax_speed(max_speed);
        body.setBody_length(body_length);
        body.setBody_width(body_width);
        body.setBody_height(body_height);
        body.setBody_style(body_style);
        car.setBody(body);
        engine.setEngine_cylinders(engine_cylinders);
        engine.setEngine_displacement(engine_displacement);
        engine.setEngine_horsepower(engine_horsepower);
        engine.setEngine_type(engine_type);
        car.setEngine(engine);
        return car;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getYear_range() {
        return year_range;
    }

    public void setYear_range(String year_range) {
        this.year_range = year_range;
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

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getWheel_drive() {
        return wheel_drive;
    }

    public void setWheel_drive(String wheel_drive) {
        this.wheel_drive = wheel_drive;
    }

    public Integer getBody_length() {
        return body_length;
    }

    public void setBody_length(Integer body_length) {
        this.body_length = body_length;
    }

    public Integer getBody_width() {
        return body_width;
    }

    public void setBody_width(Integer body_width) {
        this.body_width = body_width;
    }

    public Integer getBody_height() {
        return body_height;
    }

    public void setBody_height(Integer body_height) {
        this.body_height = body_height;
    }

    public String getBody_style() {
        return body_style;
    }

    public void setBody_style(String body_style) {
        this.body_style = body_style;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Integer getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(Integer max_speed) {
        this.max_speed = max_speed;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRequestDto car = (CarRequestDto) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", segment='" + segment + '\'' +
                ", brand_id=" + brand_id +
                ", model='" + model + '\'' +
                ", generation='" + generation + '\'' +
                ", year_range='" + year_range + '\'' +
                ", gearbox='" + gearbox + '\'' +
                '}';
    }

}
