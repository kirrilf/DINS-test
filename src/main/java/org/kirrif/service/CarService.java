package org.kirrif.service;


import org.kirrif.dto.CarRespondDto;
import org.kirrif.model.Brand;
import org.kirrif.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarService {

    List<CarRespondDto> carFilter(Optional<String> country,
                                  Optional<String> segment,
                                  Optional<Double> minEngineDisplacement,
                                  Optional<Integer> minEngineHorsepower,
                                  Optional<Integer> minMaxSpeed,
                                  Optional<String> search,
                                  Optional<Integer> year,
                                  Optional<String> bodyStyle,
                                  Optional<Boolean> isFull);

    Brand getCarBrand(Car car);


    Set<String> fuelTypes();

    Set<String> engineTypes();

    Set<String> bodyStyles();

    Set<String> wheelDrives();

    Set<String> gearboxes();

    int averageMaxSpeed(Optional<String> brand, Optional<String> model);
}
