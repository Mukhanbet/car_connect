package com.example.car_connect.service;

import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.image.CarImageResponse;

import java.util.List;
import java.util.UUID;

public interface CarService {
    void register(CarRegisterRequest request);
}
