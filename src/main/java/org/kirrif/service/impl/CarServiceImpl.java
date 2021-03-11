package org.kirrif.service.impl;

import org.kirrif.components.CarsComponent;
import org.kirrif.model.Brand;
import org.kirrif.model.Car;
import org.kirrif.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService {

    private final CarsComponent carsComponent;

    @Autowired
    public CarServiceImpl(CarsComponent carsComponent) {
        this.carsComponent = carsComponent;
    }


    @Override
    public List<Car> carFilter(Optional<String> country,
                               Optional<String> segment,
                               Optional<Double> minEngineDisplacement,
                               Optional<Integer> minEngineHorsepower,
                               Optional<Integer> minMaxSpeed,
                               Optional<String> search,
                               Optional<Integer> year,
                               Optional<String> bodyStyle) {

        return carsComponent.getCars().stream()
                .filter(car -> !country.isPresent() || country.get().equals(Objects.requireNonNull(getCarBrand(car)).getCountry()))//если параметр есть, то проверяем условие с этим параметром, если параметра нет, то условие дальше проверяться не будет
                .filter(car -> !segment.isPresent() || segment.get().equals(car.getSegment()))
                .filter(car -> !minEngineDisplacement.isPresent() || minEngineDisplacement.get() * 1000 <= car.getEngine().getEngine_displacement())
                .filter(car -> !minEngineHorsepower.isPresent() || minEngineHorsepower.get() <= car.getEngine().getEngine_horsepower())
                .filter(car -> !minMaxSpeed.isPresent() || minMaxSpeed.get() <= car.getMax_speed())
                .filter(car -> !search.isPresent() || (car.getModel().contains(search.get()) || car.getModification().contains(search.get()) || car.getGeneration().contains(search.get())))
                .filter(car -> !bodyStyle.isPresent() || isInThatBodyStyle(car, bodyStyle.get()))
                .filter(car -> !year.isPresent() || isReleasedThatYear(car, year.get()))
                .collect(Collectors.toList());


    }

    @Override
    public Brand getCarBrand(Car car) {
        List<Brand> brands = carsComponent.getBrands();
        for (Brand brand : brands) {
            if (brand.getId().equals(car.getBrand_id())) {
                return brand;
            }
        }
        return null;
    }

    @Override
    public Set<String> fuelTypes() {
        Set<String> fuelTypes = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            fuelTypes.add(car.getEngine().getEngine_type());
        }
        return fuelTypes;
    }

    @Override
    public Set<String> engineTypes() {
        Set<String> engineTypes = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            engineTypes.add(car.getEngine().getEngine_type());
        }
        return engineTypes;
    }

    @Override
    public Set<String> bodyStyles() {
        Set<String> bodyStyles = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            bodyStyles.addAll(getCarBodyStyles(car));
        }
        return bodyStyles;
    }

    @Override
    public Set<String> wheelDrives() {
        Set<String> wheelDrives = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            wheelDrives.add(car.getWheel_drive());
        }
        return wheelDrives;
    }

    @Override
    public Set<String> gearboxes() {
        Set<String> gearboxes = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            gearboxes.add(car.getGearbox());
        }
        return gearboxes;
    }

    @Override
    public int averageMaxSpeed(Optional<String> brand, Optional<String> model) {
        int sumSpeed = 0, count = 0;
        if (brand.isPresent()) {
            for (Car car : carsComponent.getCars()) {
                if (brand.get().equals(Objects.requireNonNull(getCarBrand(car)).getTitle())) {
                    sumSpeed += car.getMax_speed();
                    count++;
                }
            }
        } else if (model.isPresent()) {
            for (Car car : carsComponent.getCars()) {
                if (model.get().equals(car.getModel())) {
                    sumSpeed += car.getMax_speed();
                    count++;
                }
            }

        }
        return sumSpeed;
    }


    private boolean isReleasedThatYear(Car car, int year) {
        int since = Integer.parseInt(car.getYear_range().substring(0, 4));
        int until;
        if (car.getYear_range().endsWith("present")) {
            Calendar calendar = Calendar.getInstance();
            until = calendar.get(Calendar.YEAR);
        } else {
            until = Integer.parseInt(car.getYear_range().substring(5));
        }

        return since <= year && year <= until;

    }

    private boolean isInThatBodyStyle(Car car, String assignBodyStyle) {
        String[] bodyStyles = car.getBody().getBody_style().split(",\\s+");
        for (String bodyStyle : bodyStyles) {
            if (bodyStyle.equals(assignBodyStyle)) return true;
        }
        return false;
    }

    private List<String> getCarBodyStyles(Car car) {
        return Arrays.asList(car.getBody().getBody_style().split(",\\s+"));
    }

}
