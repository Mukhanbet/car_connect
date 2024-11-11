package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.domain.Review;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        car.setDescription(request.getDescription());
        car.setRating(0.0);
        return car;
    }

    @Override
    public CarResponse toCarResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setMake(car.getMake());
        response.setModel(car.getModel());
        response.setYear(car.getYear());
        response.setPrice(car.getPrice());
        if (car.getImages() != null && !car.getImages().isEmpty()) {
            for (CarImage image : car.getImages()) {
                response.getImagesPaths().add(image.getPath());
            }
        } else {
            response.setImagesPaths(null);
        }
        return response;
    }

    @Override
    public CarResponseDetail toResponseDetail(Car car) {
        CarResponseDetail detail = new CarResponseDetail();
        detail.setId(car.getId());
        detail.setMake(car.getMake());
        detail.setModel(car.getModel());
        detail.setColor(car.getColor());
        detail.setYear(car.getYear());
        detail.setPrice(car.getPrice());
        detail.setLocation(car.getLocation());
        detail.setAvailableFrom(car.getAvailableFrom());
        detail.setRating(car.getRating());
        if (car.getImages() != null && !car.getImages().isEmpty()) {
            for (CarImage image : car.getImages()) {
                detail.getImages().add(image.getPath());
            }
        } else {
            detail.setImages(null);
        }
        detail.setDescription(car.getDescription());
        return detail;
    }

    @Override
    public List<CarResponse> toCarResponseList(List<Car> cars) {
        List<CarResponse> carResponses = new ArrayList<>();
        for (Car car : cars) {
            carResponses.add(toCarResponse(car));
        }
        return carResponses;
    }
}
