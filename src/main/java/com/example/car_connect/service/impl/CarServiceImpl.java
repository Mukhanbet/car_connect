package com.example.car_connect.service.impl;

import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @Override
    public void register(CarRegisterRequest request) {
        carRepository.save(carMapper.toCar(request));
    }
}
