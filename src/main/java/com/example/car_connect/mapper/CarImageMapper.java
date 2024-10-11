package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;

import java.util.List;

public interface CarImageMapper {
    CarImage toCarImage(String fileName, Car car);
    CarImageResponse toCarImageResponse(CarImage carImage);
    List<CarImageResponse> toCarImageResponseList(List<CarImage> carImages);
}
