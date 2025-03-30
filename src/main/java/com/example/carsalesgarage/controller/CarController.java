package com.example.carsalesgarage.controller;

import com.example.carsalesgarage.dto.CarDTO;
import com.example.carsalesgarage.model.Car;
import com.example.carsalesgarage.model.FuelType;
import com.example.carsalesgarage.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController {

    @Autowired
    private CarService carService;


    @PostMapping
    @Operation(summary = "Add a new car to the catalog")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        try {
            Car savedCar = carService.addCar(car);
            return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get all cars by fuel type and max price")
    public ResponseEntity<List<CarDTO>> getCarsByFuelAndPrice(
            @RequestParam FuelType fuelType,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(carService.getCarsByFuelTypeAndPrice(fuelType, maxPrice));
    }

    @GetMapping("/makes")
    @Operation(summary = "Get all unique makes available in the catalog")
    public ResponseEntity<List<String>> getAllMakes() {
        return ResponseEntity.ok(carService.getAllMakes());
    }

    @PatchMapping("/{id}/picture")
    @Operation(summary = "Update a car's picture")
    public ResponseEntity<Car> updateCarPicture(@PathVariable Long id, @RequestParam String pictureUrl) {
        return ResponseEntity.ok(carService.updateCarPicture(id, pictureUrl));
    }
}
