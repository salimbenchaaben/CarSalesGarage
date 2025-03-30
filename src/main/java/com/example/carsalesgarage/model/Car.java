package com.example.carsalesgarage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @Past(message = "Registration date must be in the past")
    @NotNull(message = "Registration date is required")
    private LocalDate registrationDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @NotNull(message = "Price is required")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Fuel type is required")
    private FuelType fuelType;

    @Min(value = 0, message = "Mileage cannot be negative")
    @NotNull(message = "Mileage is required")
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transmission type is required")
    private Transmission transmission;

    private String picture;

    @PrePersist
    @PreUpdate
    private void validateRegistrationDate() {
        if (registrationDate != null && registrationDate.isBefore(LocalDate.of(2015, 1, 1))) {
            throw new IllegalArgumentException("Only cars registered after 2015 can be added.");
        }
    }
}
