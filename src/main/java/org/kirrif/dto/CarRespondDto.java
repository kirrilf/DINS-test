package org.kirrif.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.kirrif.model.*;

import java.util.Objects;

public class CarRespondDto {
    @JsonView(Views.BasicCar.class)
    private Long id;

    @JsonView(Views.BasicCar.class)
    private String segment;

    @JsonView(Views.FullCar.class)
    private Long brand_id;

    @JsonView(Views.BasicCar.class)
    private String country;

    @JsonView(Views.BasicCar.class)
    private String brand;

    @JsonView(Views.BasicCar.class)
    private String model;

    @JsonView(Views.BasicCar.class)
    private String generation;

    @JsonView(Views.BasicCar.class)
    private String modification;

    @JsonView(Views.FullCar.class)
    private String year_range;

    @JsonView(Views.AdvancedCar.class)
    private Engine engine;

    @JsonView(Views.FullCar.class)
    private String gearbox;

    @JsonView(Views.FullCar.class)
    private String wheel_drive;

    @JsonView(Views.AdvancedCar.class)
    private Body body;

    @JsonView(Views.FullCar.class)
    private Double acceleration;

    @JsonView(Views.FullCar.class)
    private Integer max_speed;

    public static CarRespondDto fromCar(Car car, Brand brand){
        CarRespondDto carRespondDto = new CarRespondDto();
        carRespondDto.setId(car.getId());
        carRespondDto.setSegment(car.getSegment());
        carRespondDto.setBrand_id(car.getBrand_id());
        carRespondDto.setCountry(brand.getCountry());
        carRespondDto.setBrand(brand.getTitle());
        carRespondDto.setModel(car.getModel());
        carRespondDto.setGeneration(car.getGeneration());
        carRespondDto.setModification(car.getModification());
        carRespondDto.setYear_range(car.getYear_range());
        carRespondDto.setEngine(car.getEngine());
        carRespondDto.setGearbox(car.getGearbox());
        carRespondDto.setWheel_drive(car.getWheel_drive());
        carRespondDto.setBody(car.getBody());
        carRespondDto.setAcceleration(car.getAcceleration());
        carRespondDto.setMax_speed(car.getMax_speed());
        return carRespondDto;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
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

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
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
        CarRespondDto that = (CarRespondDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarRespondDto{" +
                "id=" + id +
                ", segment='" + segment + '\'' +
                ", country='" + country + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", generation='" + generation + '\'' +
                '}';
    }
}
