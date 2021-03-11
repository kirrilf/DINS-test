package org.kirrif.dto;

import org.kirrif.model.Brand;
import org.kirrif.model.Car;

import java.util.Objects;

public class CarRespondDto {
    private Long id;
    private String segment;
    private String country;
    private String brand;
    private String model;
    private String generation;
    private String modification;


    public static CarRespondDto fromCar(Car car, Brand brand){
        CarRespondDto carRespondDto = new CarRespondDto();
        setFields(car, brand, carRespondDto);
        return carRespondDto;
    }


    protected static void setFields(Car car, Brand brand, CarRespondDto carRespondDto){
        carRespondDto.setId(car.getId());
        carRespondDto.setSegment(car.getSegment());
        carRespondDto.setCountry(brand.getCountry());
        carRespondDto.setBrand(brand.getTitle());
        carRespondDto.setModel(car.getModel());
        carRespondDto.setGeneration(car.getGeneration());
        carRespondDto.setModification(car.getModification());
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
}
