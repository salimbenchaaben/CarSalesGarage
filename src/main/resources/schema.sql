-- src/main/resources/schema.sql

CREATE TABLE IF NOT EXISTS car (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    registration_date DATE,
    price DECIMAL(10, 2),
    fuel_type VARCHAR(50),
    mileage INT,
    transmission VARCHAR(50),
    picture VARCHAR(255)
    );
