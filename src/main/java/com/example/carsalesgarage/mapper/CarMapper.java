package com.example.carsalesgarage.mapper;

import com.example.carsalesgarage.dto.CarDTO;
import com.example.carsalesgarage.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carToCarDTO(Car car);
    Car carDTOToCar(CarDTO carDTO);
    List<CarDTO> carsToCarDTOs(List<Car> cars);
}
