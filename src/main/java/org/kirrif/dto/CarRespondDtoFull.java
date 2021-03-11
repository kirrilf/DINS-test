package org.kirrif.dto;

import org.kirrif.model.Body;
import org.kirrif.model.Brand;
import org.kirrif.model.Car;
import org.kirrif.model.Engine;

public class CarRespondDtoFull extends CarRespondDto {

    private Engine engine;
    private Body body;
    private Double acceleration;


    public static CarRespondDtoFull fromCar(Car car, Brand brand) {
        CarRespondDtoFull carRespondDtoFull = new CarRespondDtoFull();
        setFields(car, brand, carRespondDtoFull);
        carRespondDtoFull.setEngine(car.getEngine());
        carRespondDtoFull.setBody(car.getBody());
        carRespondDtoFull.setAcceleration(car.getAcceleration());
        return carRespondDtoFull;
    }


    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
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
}
