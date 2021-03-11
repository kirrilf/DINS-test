package org.kirrif.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.kirrif.dto.CarRespondDto;
import org.kirrif.dto.CarRespondDtoFull;
import org.kirrif.model.Car;
import org.kirrif.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/api")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping(value = "/cars")
    public ResponseEntity<List<CarRespondDto>> getCar(@RequestParam Optional<String> country,
                                                      @RequestParam Optional<String> segment,
                                                      @RequestParam Optional<Double> minEngineDisplacement,
                                                      @RequestParam Optional<Integer> minEngineHorsepower,
                                                      @RequestParam Optional<Integer> minMaxSpeed,
                                                      @RequestParam Optional<String> search,
                                                      @RequestParam Optional<Boolean> isFull,
                                                      @RequestParam Optional<Integer> year,
                                                      @RequestParam Optional<String> bodyStyle){

        List<Car> carsAfterFilters = carService.carFilter(country,
                segment,
                minEngineDisplacement,
                minEngineHorsepower,
                minMaxSpeed,
                search,
                year,
                bodyStyle);


        if (carsAfterFilters.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        if (isFull.orElse(false)) {
            return ResponseEntity
                    .ok()
                    .body(carsAfterFilters
                            .stream()
                            .map(car -> CarRespondDtoFull.fromCar(car, Objects.requireNonNull(carService.getCarBrand(car))))
                            .collect(Collectors.toList()));
        }

        return ResponseEntity
                .ok()
                .body(carsAfterFilters
                        .stream()
                        .map(car -> CarRespondDto.fromCar(car, Objects.requireNonNull(carService.getCarBrand(car))))
                        .collect(Collectors.toList()));
    }


    @GetMapping(value = "/fuel-types")
    public Set<String> getFuelTypes() {
        return carService.fuelTypes();
    }

    @GetMapping(value = "/engine-types")
    public Set<String> getEngineTypes() {
        return carService.engineTypes();
    }

    @GetMapping(value = "/body-styles")
    public Set<String> getBodyStyles() {
        return carService.bodyStyles();
    }


    @GetMapping(value = "/wheel-drives")
    public Set<String> getWheelDrives() {
        return carService.wheelDrives();
    }

    @GetMapping(value = "/gearboxes")
    public Set<String> getGearboxes() {
        return carService.gearboxes();
    }

    @GetMapping(value = "/max-speed")
    public ResponseEntity<Integer> getAverageMaxSpeed(@RequestParam Optional<String> brand,
                                                      @RequestParam Optional<String> model) {
        if ((brand.isPresent() && model.isPresent()) || (brand.isEmpty() && model.isEmpty())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int averageSpeed = carService.averageMaxSpeed(brand, model);
        if (averageSpeed == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(averageSpeed, HttpStatus.OK);
        }
    }


}
