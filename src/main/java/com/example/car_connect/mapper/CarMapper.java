package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.dto.car.CarRegisterRequest;

public interface CarMapper {
    Car toCar(CarRegisterRequest request);
}
