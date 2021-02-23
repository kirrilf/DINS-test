package org.kirrilf.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kirrif.components.CarsComponent;
import org.kirrif.model.Body;
import org.kirrif.model.Brand;
import org.kirrif.model.Car;
import org.kirrif.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = org.kirrif.DinsTestApplication.class)
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockBean
   private CarsComponent mock;

    @Before
    public void init(){
        List<Car> cars = new ArrayList<>();

        Engine engine1 = new Engine(
                "GASOLINE",
                "L4",
                1498,
                150
        );

        Body body1 = new Body(
                4284,
                1789,
                1491,
                "Hatchback"
        );

        Car car1 = new Car(
                1L,
                "C-segment",
                1L,
                "Golf",
                "Mk8",
                "1.5 TSI",
                "2019-present",
                engine1,
                "ROBOTIC",
                "FWD",
                body1,
                8.5,
                224
        );

        Engine engine2 = new Engine(
                "DIESEL",
                "L4",
                1995,
                116
        );

        Body body2 = new Body(
                4410,
                1710,
                1470,
                "Sedan,  Wagon,  Hatchback"
        );

        Car car2 = new Car(
                2L,
                "C-segment",
                2L,
                "Corolla",
                "IX generation",
                "2.0 D",
                "1990-present",
                engine2,
                "ROBOTIC",
                "FWD",
                body2,
                8.6,
                220
        );



        cars.add(car1);
        cars.add(car2);

        List<Brand> brands = new ArrayList<>();
        Brand brand1 = new Brand(
                1L,
                "Volkswagen",
                "Germany"
        );
        Brand brand2 = new Brand(
                2L,
                "Toyota",
                "Japan"
        );
        brands.add(brand1);
        brands.add(brand2);


        when(mock.getCars()).thenReturn(cars);
        when(mock.getBrands()).thenReturn(brands);

    }

    @Test
    public void testGetCarsWithoutParams() throws Exception{

        mockMvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$[0].segment", is("C-segment")))
                .andExpect(jsonPath("$[0].country", is("Germany")))
                .andExpect(jsonPath("$[0].brand", is("Volkswagen")))
                .andExpect(jsonPath("$[0].model", is("Golf")))
                .andExpect(jsonPath("$[0].generation", is("Mk8")))
                .andExpect(jsonPath("$[0].modification", is("1.5 TSI")));

    }

    @Test
    public void testGetCarsWithIsFull() throws Exception{

        mockMvc.perform(get("/api/cars?isFull=true")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$[0].segment", is("C-segment")))
                .andExpect(jsonPath("$[0].country", is("Germany")))
                .andExpect(jsonPath("$[0].brand", is("Volkswagen")))
                .andExpect(jsonPath("$[0].model", is("Golf")))
                .andExpect(jsonPath("$[0].generation", is("Mk8")))
                .andExpect(jsonPath("$[0].modification", is("1.5 TSI")))
                .andExpect(jsonPath("$[0].engine.engine_type", is("GASOLINE")))
                .andExpect(jsonPath("$[0].engine.engine_cylinders", is("L4")))
                .andExpect(jsonPath("$[0].engine.engine_displacement", is(1498)))
                .andExpect(jsonPath("$[0].engine.engine_horsepower", is(150)))
                .andExpect(jsonPath("$[0].body.body_length", is(4284)))
                .andExpect(jsonPath("$[0].body.body_width", is(1789)))
                .andExpect(jsonPath("$[0].body.body_height", is(1491)))
                .andExpect(jsonPath("$[0].body.body_style", is("Hatchback")));

    }

    @Test
    public void testGetCarsWithIsFullAndCountry() throws Exception{

        mockMvc.perform(get("/api/cars?isFull=true&country=Japan")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(2)))
                .andExpect(jsonPath("$[0].segment", is("C-segment")))
                .andExpect(jsonPath("$[0].country", is("Japan")))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[0].model", is("Corolla")))
                .andExpect(jsonPath("$[0].generation", is("IX generation")))
                .andExpect(jsonPath("$[0].modification", is("2.0 D")))
                .andExpect(jsonPath("$[0].engine.engine_type", is("DIESEL")))
                .andExpect(jsonPath("$[0].engine.engine_cylinders", is("L4")))
                .andExpect(jsonPath("$[0].engine.engine_displacement", is(1995)))
                .andExpect(jsonPath("$[0].engine.engine_horsepower", is(116)))
                .andExpect(jsonPath("$[0].body.body_length", is(4410)))
                .andExpect(jsonPath("$[0].body.body_width", is(1710)))
                .andExpect(jsonPath("$[0].body.body_height", is(1470)))
                .andExpect(jsonPath("$[0].body.body_style", is("Sedan,  Wagon,  Hatchback")));
    }

    @Test
    public void testGetCarsWithBadTypeOfParameter() throws Exception{
        mockMvc.perform(get("/api/cars?minEngineDisplacement=test")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCarsWithBadSearchParameter() throws Exception{
        mockMvc.perform(get("/api/cars?search=what's_not_there")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetFuelTypes() throws Exception{
        mockMvc.perform(get("/api/fuel-types")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]", is("DIESEL")))
                .andExpect(jsonPath("$[1]", is("GASOLINE")));
    }

    @Test
    public void testGetBodyStyles() throws Exception{
        mockMvc.perform(get("/api/body-styles")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]", is("Wagon")))
                .andExpect(jsonPath("$[1]", is("Hatchback")))
                .andExpect(jsonPath("$[2]", is("Sedan")));
    }


    @Test
    public void testGetWheelDrives() throws Exception{
        mockMvc.perform(get("/api/wheel-drives")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]", is("FWD")));
    }

    @Test
    public void testGetGearboxes() throws Exception{
        mockMvc.perform(get("/api/gearboxes")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0]", is("ROBOTIC")));
    }

    @Test
    public void testGetAverageMaxSpeedWithBrand() throws Exception{
        mockMvc.perform(get("/api/max-speed?brand=Toyota")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", is(220)));
    }

    @Test
    public void testGetAverageMaxSpeedWithModel() throws Exception{
        mockMvc.perform(get("/api/max-speed?model=Golf")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", is(224)));
    }

    @Test
    public void testGetAverageMaxSpeedWithModelAndBrand() throws Exception{
        mockMvc.perform(get("/api/max-speed?model=Corolla&brand=Toyota")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAverageMaxSpeedWithNonExistentBrand() throws Exception {
        mockMvc.perform(get("/api/max-speed?brand=test")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testGetAverageMaxSpeedWithNonExistentModel() throws Exception {
        mockMvc.perform(get("/api/max-speed?model=test")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }




}
