package com.example.carsalesgarage.service;

import com.example.carsalesgarage.dto.CarDTO;
import com.example.carsalesgarage.model.Car;
import com.example.carsalesgarage.model.FuelType;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    Car addCar(Car car);
    List<CarDTO> getCarsByFuelTypeAndPrice(FuelType fuelType, BigDecimal maxPrice);
    List<String> getAllMakes();
    Car updateCarPicture(Long id, String pictureUrl);
}
