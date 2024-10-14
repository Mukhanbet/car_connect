package com.example.car_connect.service;

import com.example.car_connect.model.dto.car.CarFilter;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.image.CarImageResponse;

import java.util.List;
import java.util.UUID;

public interface CarService {
    void register(CarRegisterRequest request);
    List<CarResponse> search(CarFilter filter, int page, int size);
}
