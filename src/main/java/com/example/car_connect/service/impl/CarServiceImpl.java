package com.example.car_connect.service.impl;

import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.dto.car.CarFilter;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.CarService;
import com.example.car_connect.specification.CarSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public void register(CarRegisterRequest request) {
        carRepository.save(carMapper.toCar(request));
    }

    @Override
    public List<CarResponse> search(CarFilter filter, int page, int size) {
        Specification<Car> specification = CarSpecification.filterByCriteria(filter);
        Pageable pageable = PageRequest.of(page, size);
        return carMapper.toCarResponseList(carRepository.findAll(specification, pageable).stream().toList());
    }
}
