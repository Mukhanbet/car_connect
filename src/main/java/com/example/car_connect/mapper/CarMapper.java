package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;

import java.util.List;

public interface CarMapper {
    Car toCar(CarRegisterRequest request, User owner);
    CarResponse toCarResponse(Car car);
    CarResponseDetail toResponseDetail(Car car);
    List<CarResponse> toCarResponseList(List<Car> cars);
}
