package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.CarMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.Image;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMapperImpl implements CarMapper {
    @Override
    public Car toCar(CarRegisterRequest request, User owner) {
        Car car = new Car();
        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setTransmission(request.getTransmission());
        car.setMileage(request.getMileage());
        car.setColor(request.getColor());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
        car.setLocation(request.getLocation());
        car.setAvailableFrom(request.getAvailableFrom());
        car.setDescription(request.getDescription());
        car.setRating(0.0);
        car.setOwner(owner);
        return car;
    }

    @Override
    public CarResponse toCarResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setId(car.getId());
        response.setMake(car.getMake());
        response.setModel(car.getModel());
        response.setTransmission(car.getTransmission());
        response.setMileage(car.getMileage());
        response.setYear(car.getYear());
        response.setPrice(car.getPrice());
        if (car.getFonImage() != null) {
            response.setFonImage(car.getFonImage().getPath());
        }
        return response;
    }

    @Override
    public CarResponseDetail toResponseDetail(Car car) {
        CarResponseDetail detail = new CarResponseDetail();
        detail.setId(car.getId());
        detail.setMake(car.getMake());
        detail.setModel(car.getModel());
        detail.setTransmission(car.getTransmission());
        detail.setMileage(car.getMileage());
        detail.setColor(car.getColor());
        detail.setYear(car.getYear());
        detail.setPrice(car.getPrice());
        detail.setLocation(car.getLocation());
        detail.setAvailableFrom(car.getAvailableFrom());
        detail.setRating(car.getRating());
        List<String> images = new ArrayList<>();
        if (car.getImages() != null && !car.getImages().isEmpty()) {
            for (Image image : car.getImages()) {
                images.add(image.getPath());
            }
            detail.setImages(images);
        } else {
            detail.setImages(null);
        }
        detail.setFonImage(car.getFonImage().getPath());
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
