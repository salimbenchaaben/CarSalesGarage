package com.example.carsalesgarage.controller;

import com.example.carsalesgarage.model.Car;
import com.example.carsalesgarage.model.FuelType;
import com.example.carsalesgarage.model.Transmission;
import com.example.carsalesgarage.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
        car = Car.builder()
                .make("Tesla")
                .model("Model S")
                .registrationDate(LocalDate.of(2020, 1, 1))
                .price(BigDecimal.valueOf(80000))
                .fuelType(FuelType.ELECTRIC)
                .mileage(20000)
                .transmission(Transmission.AUTOMATIC)
                .picture("tesla.jpg")
                .build();
        carRepository.save(car);
    }

    @Test
    void addCar_ShouldReturnCreatedCar() throws Exception {
        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "make": "BMW",
                                "model": "X5",
                                "registrationDate": "2019-06-15",
                                "price": 45000,
                                "fuelType": "DIESEL",
                                "mileage": 30000,
                                "transmission": "AUTOMATIC",
                                "picture": "bmw.jpg"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make").value("BMW"));
    }

    @Test
    void getCarsByFuelAndPrice_ShouldReturnCars() throws Exception {
        mockMvc.perform(get("/api/cars")
                        .param("fuelType", "ELECTRIC")
                        .param("maxPrice", "100000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].make").value("Tesla"));
    }

    @Test
    void getAllMakes_ShouldReturnMakesList() throws Exception {
        mockMvc.perform(get("/api/cars/makes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").value("Tesla"));
    }

    @Test
    void updateCarPicture_ShouldUpdatePicture() throws Exception {
        mockMvc.perform(patch("/api/cars/" + car.getId() + "/picture")
                        .param("pictureUrl", "new_tesla.jpg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.picture").value("new_tesla.jpg"));
    }
}
