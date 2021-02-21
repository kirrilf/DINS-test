package org.kirrif.service;

import org.kirrif.model.Brand;
import org.kirrif.model.Car;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class CarService {

    public static Brand getCarBrand(Car car, List<Brand> brands){
        for(Brand brand : brands){
            if(brand.getId().equals(car.getBrand_id())){
                return brand;
            }
        }
        return null;
    }

    public static boolean isReleasedThatYear(Car car, int year) {
        int since = Integer.parseInt(car.getYear_range().substring(0, 4));
        int until;
        if(car.getYear_range().endsWith("present")){
            Calendar calendar = Calendar.getInstance();
            until = calendar.get(Calendar.YEAR);
        }else {
            until = Integer.parseInt(car.getYear_range().substring(5));
        }

        return since <= year && year <= until;

    }

    public static boolean isInThatBodyStyle(Car car, String assignBodyStyle) {
        String[] bodyStyles = car.getBody().getBody_style().split(",\\s+");
        for(String bodyStyle : bodyStyles){
            if(bodyStyle.equals(assignBodyStyle))return true;
        }
        return false;
    }

    public static List<String> getCarBodyStyles(Car car){
        return Arrays.asList(car.getBody().getBody_style().split(",\\s+"));
    }

}
