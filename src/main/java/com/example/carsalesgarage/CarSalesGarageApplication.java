package com.example.carsalesgarage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CarSalesGarageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSalesGarageApplication.class, args);
    }

}
