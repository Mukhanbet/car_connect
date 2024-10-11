package com.example.car_connect.service.impl;

import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.CarImageMapper;
import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.repository.CarImageRepository;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;
    private final CarMapper carMapper;
    private final CarImageMapper carImageMapper;

    @Override
    public void register(CarRegisterRequest request) {
        carRepository.save(carMapper.toCar(request));
    }

    @Override
    public List<CarImageResponse> getImages(UUID carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        return carImageMapper.toCarImageResponseList(carImageRepository.findAllByCar(car));
    }
}
