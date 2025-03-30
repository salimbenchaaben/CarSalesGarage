-- src/main/resources/data.sql

INSERT INTO car (make, model, registration_date, price, fuel_type, mileage, transmission, picture)
VALUES
    ('Toyota', 'Corolla', '2016-01-01', 15000, 'DIESEL', 50000, 'MANUAL', 'toyota_corolla.jpg'),
    ('Tesla', 'Model 3', '2020-06-15', 35000, 'ELECTRIC', 20000, 'AUTOMATIC', 'tesla_model_3.jpg'),
    ('Honda', 'Civic', '2018-05-20', 18000, 'HYBRID', 45000, 'SEMI_AUTOMATIC', 'honda_civic.jpg');
