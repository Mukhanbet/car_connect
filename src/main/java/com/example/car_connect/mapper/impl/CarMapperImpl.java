package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements CarMapper {
    @Override
    public Car toCar(CarRegisterRequest request) {
        Car car = new Car();
        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setColor(request.getColor());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
        car.setLocation(request.getLocation());
        car.setAvailableFrom(request.getAvailableFrom());
        car.setAvailableTo(request.getAvailableTo());
        car.setDescription(request.getDescription());
        car.setRating(0.0);
        return car;
    }
}
