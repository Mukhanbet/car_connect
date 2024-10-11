package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;

public interface CarImageMapper {
    CarImage toCarImage(String fileName);
    CarImageResponse toCarImageResponse(CarImage carImage);
}
