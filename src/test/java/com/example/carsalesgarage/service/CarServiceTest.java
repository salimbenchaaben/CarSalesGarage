package com.example.carsalesgarage.service;

import com.example.carsalesgarage.model.Car;
import com.example.carsalesgarage.model.FuelType;
import com.example.carsalesgarage.model.Transmission;
import com.example.carsalesgarage.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService = new CarServiceImpl();

    private Car car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        car = Car.builder()
                .id(1L)
                .make("Toyota")
                .model("Corolla")
                .registrationDate(LocalDate.of(2018, 5, 10))
                .price(BigDecimal.valueOf(15000))
                .fuelType(FuelType.HYBRID)
                .mileage(50000)
                .transmission(Transmission.AUTOMATIC)
                .picture("image.jpg")
                .build();
    }

    @Test
    void addCar_ShouldReturnSavedCar() {
        when(carRepository.save(any(Car.class))).thenReturn(car);
        Car savedCar = carService.addCar(car);
        assertNotNull(savedCar);
        assertEquals("Toyota", savedCar.getMake());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testAddCar_WithInvalidRegistrationDate() {

        car.setRegistrationDate(LocalDate.of(2015, 12, 31));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> carService.addCar(car));

        assertEquals("Only cars registered after 2015 are allowed to be added to the catalog.", exception.getMessage());
    }


    @Test
    void getAllMakes_ShouldReturnUniqueMakes() {
        when(carRepository.findAll()).thenReturn(List.of(car));
        List<String> makes = carService.getAllMakes();
        assertEquals(1, makes.size());
        assertEquals("Toyota", makes.get(0));
    }

    @Test
    void updateCarPicture_ShouldUpdatePicture() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        carService.updateCarPicture(1L, "new_image.jpg");
        assertEquals("new_image.jpg", car.getPicture());
    }
}
