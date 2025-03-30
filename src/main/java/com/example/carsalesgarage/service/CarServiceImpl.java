package com.example.carsalesgarage.service;

import com.example.carsalesgarage.dto.CarDTO;
import com.example.carsalesgarage.exception.CarNotFoundException;
import com.example.carsalesgarage.mapper.CarMapper;
import com.example.carsalesgarage.model.Car;
import com.example.carsalesgarage.model.FuelType;
import com.example.carsalesgarage.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public Car addCar(Car car) {
        if (car.getRegistrationDate().isBefore(LocalDate.of(2016, 1, 1))) {
            throw new IllegalArgumentException("Only cars registered after 2015 are allowed to be added to the catalog.");
        }
        return carRepository.save(car);
    }


    @Override
    public List<CarDTO> getCarsByFuelTypeAndPrice(FuelType fuelType, BigDecimal maxPrice) {
        List<Car> cars = carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);
        return carMapper.carsToCarDTOs(cars);
    }

    @Override
    @Cacheable("makes")
    public List<String> getAllMakes() {
        return carRepository.findAll().stream()
                .map(Car::getMake)
                .distinct()
                .toList();
    }

    @Override
    public Car updateCarPicture(Long id, String pictureUrl) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
        car.setPicture(pictureUrl);
        return carRepository.save(car);
    }
}
