package org.kirrif.components;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.kirrif.dto.CarRequestDto;
import org.kirrif.dto.PageDto;
import org.kirrif.model.Brand;
import org.kirrif.model.Car;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarsComponent {

    private List<Car> cars;
    private List<Brand> brands;

    @PostConstruct
    private void getCarsByApi() throws Exception {
        cars = new ArrayList<>();
        boolean lastPage = false;
        int pageNumber = 0;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){

            Gson gson = new Gson();
            String url = "http://localhost:8084/api/v1/cars/paged?page=";
            while (!lastPage) {
                HttpGet request = new HttpGet(url+pageNumber);
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    HttpEntity entity = response.getEntity();
                    String result = EntityUtils.toString(entity);
                    PageDto page = gson.fromJson(result, PageDto.class);
                    for(CarRequestDto carDto : page.getContent()){
                        cars.add(getAllCarCharacteristics(carDto, httpClient, gson).toCar());
                    }
                    lastPage = page.isLast();
                }
                pageNumber++;

            }
        }

    }

    private CarRequestDto getAllCarCharacteristics(CarRequestDto car, CloseableHttpClient httpClient, Gson gson) throws Exception {
            String url = "http://localhost:8084/api/v1/cars/";
            HttpGet request = new HttpGet(url+car.getId());
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                car = gson.fromJson(result, CarRequestDto.class);
            }
            return car;


    }

    @PostConstruct
    private void  getBrandsByApi() throws Exception{
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            Gson gson = new Gson();
            String url = "http://localhost:8084/api/v1/brands";
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                Type type = new TypeToken<List<Brand>>(){}.getType();
                brands = gson.fromJson(result, type);
            }
        }
    }

    public List<Car> getCars(){
        return cars;
    }

    public List<Brand> getBrands(){
        return brands;
    }



}
