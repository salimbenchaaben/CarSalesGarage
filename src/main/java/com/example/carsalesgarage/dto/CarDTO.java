package com.example.carsalesgarage.dto;

import com.example.carsalesgarage.model.FuelType;
import com.example.carsalesgarage.model.Transmission;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CarDTO(
        String make,
        String model,
        LocalDate registrationDate,
        BigDecimal price,
        FuelType fuelType,
        int mileage,
        Transmission transmission,
        String picture) {
}
