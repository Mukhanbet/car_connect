package com.example.car_connect.service.impl;

import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.car.CarFilter;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.AuthService;
import com.example.car_connect.service.CarImageService;
import com.example.car_connect.service.CarService;
import com.example.car_connect.specification.CarSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarImageService carImageService;
    private final AuthService authService;

    @Override
    public CarResponseDetail register(CarRegisterRequest request, List<MultipartFile> images, MultipartFile fonImage, String token) {
        User owner = authService.getUserFromToken(token);
        Car car = carRepository.save(carMapper.toCar(request, owner));
        carImageService.uploadCarImage(images, fonImage, car.getId());
        return carMapper.toResponseDetail(car);
    }

    @Override
    public List<CarResponse> search(CarFilter filter, int page, int size) {
        Specification<Car> specification = CarSpecification.filterByCriteria(filter);
        Pageable pageable = PageRequest.of(page, size);
        return carMapper.toCarResponseList(carRepository.findAll(specification, pageable).stream().toList());
    }

    @Override
    public CarResponseDetail getDetail(UUID id) {
        return carMapper.toResponseDetail(carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found")));
    }

    @Override
    public List<CarResponse> getRelatedCars(UUID id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        return carMapper.toCarResponseList(carRepository.findAllRelatedCars(car.getMake(), car.getModel(), car.getColor(), car.getYear(), car.getPrice(), car.getLocation(), car.getRating()));
    }

    @Override
    public List<CarResponse> all() {
        return carMapper.toCarResponseList(carRepository.findAll());
    }
}
