package org.kirrif.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.kirrif.components.Cars;
import org.kirrif.dto.CarRespondDto;
import org.kirrif.model.Brand;
import org.kirrif.model.Car;
import org.kirrif.model.Views;
import org.kirrif.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CarController {

    private final List<Car> cars;
    private final List<Brand> brands;

    public CarController() {
        this.brands = Cars.getBrands();
        this.cars = Cars.getCars();
    }


    @GetMapping("/cars")
    public ResponseEntity<String> getCar(@RequestParam Optional<String> country,
                                 @RequestParam Optional<String> segment,
                                 @RequestParam Optional<Double> minEngineDisplacement,
                                 @RequestParam Optional<Integer> minEngineHorsepower,
                                 @RequestParam Optional<Integer> minMaxSpeed,
                                 @RequestParam Optional<String> search,
                                 @RequestParam Optional<Boolean> isFull,
                                 @RequestParam Optional<Integer> year,
                                 @RequestParam Optional<String> bodyStyle) throws JsonProcessingException {

        List<Car> carsAfterFilters = cars.stream()
                .filter(car -> !country.isPresent() || country.get().equals(CarService.getCarBrand(car, brands).getCountry()))
                .filter(car -> !segment.isPresent() || segment.get().equals(car.getSegment()))
                .filter(car -> !minEngineDisplacement.isPresent() || minEngineDisplacement.get() * 1000 <= car.getEngine().getEngine_displacement())
                .filter(car -> !minEngineHorsepower.isPresent() || minEngineHorsepower.get() <= car.getEngine().getEngine_horsepower())
                .filter(car -> !minMaxSpeed.isPresent() || minMaxSpeed.get() <= car.getMax_speed())
                .filter(car -> !search.isPresent() || (car.getModel().contains(search.get()) || car.getModification().contains(search.get()) || car.getGeneration().contains(search.get())))
                .filter(car -> !bodyStyle.isPresent() || CarService.isInThatBodyStyle(car, bodyStyle.get()))
                .filter(car -> !year.isPresent() || CarService.isReleasedThatYear(car, year.get()))
                .collect(Collectors.toList());

        if(carsAfterFilters.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CarRespondDto> carRequestDtoList = carsAfterFilters.stream().map(car -> CarRespondDto.fromCar(car, CarService.getCarBrand(car, brands))).collect(Collectors.toList());

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


    @GetMapping("brands")
    public List<Brand> getBrands() {
        return Cars.getBrands();
    }


}
