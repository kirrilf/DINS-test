package org.kirrif.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.kirrif.components.CarsComponent;
import org.kirrif.dto.CarRespondDto;
import org.kirrif.model.Car;
import org.kirrif.model.Views;
import org.kirrif.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/api")
public class CarController {

    //private final List<Car> cars;
    //private final List<Brand> brands;

    private final CarsComponent carsComponent;

    @Autowired
    public CarController(CarsComponent carsComponent) {
        //this.brands = carsComponent.getBrands();
        //this.cars = carsComponent.getCars();
        this.carsComponent = carsComponent;
    }


    @GetMapping(value = "/cars")
    public ResponseEntity<String> getCar(@RequestParam Optional<String> country,
                                         @RequestParam Optional<String> segment,
                                         @RequestParam Optional<Double> minEngineDisplacement,
                                         @RequestParam Optional<Integer> minEngineHorsepower,
                                         @RequestParam Optional<Integer> minMaxSpeed,
                                         @RequestParam Optional<String> search,
                                         @RequestParam Optional<Boolean> isFull,
                                         @RequestParam Optional<Integer> year,
                                         @RequestParam Optional<String> bodyStyle) throws JsonProcessingException {

        List<Car> carsAfterFilters = carsComponent.getCars().stream()
                .filter(car -> !country.isPresent() || country.get().equals(Objects.requireNonNull(CarService.getCarBrand(car, carsComponent.getBrands())).getCountry()))//если параметр есть, то проверяем условие с этим параметром, если параметра нет, то условие дальше проверяться не будет
                .filter(car -> !segment.isPresent() || segment.get().equals(car.getSegment()))
                .filter(car -> !minEngineDisplacement.isPresent() || minEngineDisplacement.get() * 1000 <= car.getEngine().getEngine_displacement())
                .filter(car -> !minEngineHorsepower.isPresent() || minEngineHorsepower.get() <= car.getEngine().getEngine_horsepower())
                .filter(car -> !minMaxSpeed.isPresent() || minMaxSpeed.get() <= car.getMax_speed())
                .filter(car -> !search.isPresent() || (car.getModel().contains(search.get()) || car.getModification().contains(search.get()) || car.getGeneration().contains(search.get())))
                .filter(car -> !bodyStyle.isPresent() || CarService.isInThatBodyStyle(car, bodyStyle.get()))
                .filter(car -> !year.isPresent() || CarService.isReleasedThatYear(car, year.get()))
                .collect(Collectors.toList());

        if (carsAfterFilters.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CarRespondDto> carRequestDtoList = carsAfterFilters
                .stream()
                .map(car -> CarRespondDto.fromCar(car, Objects.requireNonNull(CarService.getCarBrand(car, carsComponent.getBrands()))))
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (isFull.orElse(false)) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(mapper.writerWithView(Views.AdvancedCar.class).writeValueAsString(carRequestDtoList));
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(mapper.writerWithView(Views.BasicCar.class).writeValueAsString(carRequestDtoList));
    }


    @GetMapping(value = "/fuel-types")
    public Set<String> getFuelTypes() {
        Set<String> fuelTypes = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            fuelTypes.add(car.getEngine().getEngine_type());
        }
        return fuelTypes;
    }

    @GetMapping(value = "/engine-types")
    public Set<String> getEngineTypes() {
        Set<String> engineTypes = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            engineTypes.add(car.getEngine().getEngine_type());
        }
        return engineTypes;
    }

    @GetMapping(value = "/body-styles")
    public Set<String> getBodyStyles() {
        Set<String> bodyStyles = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            bodyStyles.addAll(CarService.getCarBodyStyles(car));
        }
        return bodyStyles;
    }




    @GetMapping(value = "/wheel-drives")
    public Set<String> getWheelDrives() {
        Set<String> wheelDrives = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            wheelDrives.add(car.getWheel_drive());
        }
        return wheelDrives;
    }

    @GetMapping(value = "/gearboxes")
    public Set<String> getGearboxes() {
        Set<String> gearboxes = new HashSet<>();
        for (Car car : carsComponent.getCars()) {
            gearboxes.add(car.getGearbox());
        }
        return gearboxes;
    }

    @GetMapping(value = "/max-speed")
    public ResponseEntity<Integer> getAverageMaxSpeed(@RequestParam Optional<String> brand,
                                                      @RequestParam Optional<String> model) {
        if (brand.isPresent() && model.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int sumSpeed = 0, count = 0;
        if (brand.isPresent()) {
            for (Car car : carsComponent.getCars()) {
                if (brand.get().equals(Objects.requireNonNull(CarService.getCarBrand(car, carsComponent.getBrands())).getTitle())) {
                    sumSpeed += car.getMax_speed();
                    count++;
                }
            }
            if (count == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(sumSpeed / count, HttpStatus.OK);
            }
        }
        if (model.isPresent()) {
            for (Car car : carsComponent.getCars()) {
                if (model.get().equals(car.getModel())) {
                    sumSpeed += car.getMax_speed();
                    count++;
                }
            }
            if (count == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(sumSpeed / count, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
