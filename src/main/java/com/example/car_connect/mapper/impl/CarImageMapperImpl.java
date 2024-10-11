package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.CarImageMapper;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.stereotype.Component;

@Component
public class CarImageMapperImpl implements CarImageMapper {
    @Override
    public CarImage toCarImage(String fileName) {
        CarImage carImage = new CarImage();
        carImage.setName(fileName);
        carImage.setPath("localhost:2020/carImages/" + fileName);
        return carImage;
    }

    @Override
    public CarImageResponse toCarImageResponse(CarImage carImage) {
        CarImageResponse imageResponse = new CarImageResponse();
        imageResponse.setId(carImage.getId());
        imageResponse.setName(carImage.getName());
        imageResponse.setPath(carImage.getPath());
        return imageResponse;
    }
}
