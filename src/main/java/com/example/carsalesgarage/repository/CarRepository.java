package com.example.carsalesgarage.repository;

import com.example.carsalesgarage.model.Car;
import com.example.carsalesgarage.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, BigDecimal maxPrice);
}
