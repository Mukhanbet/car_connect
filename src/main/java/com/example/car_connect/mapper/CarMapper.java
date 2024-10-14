package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;

import java.util.List;

public interface CarMapper {
    Car toCar(CarRegisterRequest request);
    CarResponse toCarResponse(Car car);
    List<CarResponse> toCarResponseList(List<Car> cars);
}
