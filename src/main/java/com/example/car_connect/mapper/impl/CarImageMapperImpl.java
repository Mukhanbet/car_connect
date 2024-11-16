package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.CarImageMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarFonImageResponse;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarImageMapperImpl implements CarImageMapper {
    @Override
    public CarImage toCarImage(String fileName, String path) {
        CarImage carImage = new CarImage();
        carImage.setName(fileName);
        carImage.setPath(path);
        return carImage;
    }

    @Override
    public CarFonImageResponse toCarFonImageResponse(CarImage carImage, List<CarImage> images) {
        CarFonImageResponse response = new CarFonImageResponse();
        response.setId(carImage.getId());
        response.setName(carImage.getName());
        response.setPath(carImage.getPath());
        response.setImages(toCarImageResponseList(images));
        return response;
    }

    @Override
    public CarImageResponse toCarImageResponse(CarImage carImage) {
        CarImageResponse imageResponse = new CarImageResponse();
        imageResponse.setId(carImage.getId());
        imageResponse.setName(carImage.getName());
        imageResponse.setPath(carImage.getPath());
        return imageResponse;
    }

    @Override
    public List<CarImageResponse> toCarImageResponseList(List<CarImage> carImages) {
        List<CarImageResponse> carImageResponseList = new ArrayList<>();
        for (CarImage carImage : carImages) {
            carImageResponseList.add(toCarImageResponse(carImage));
        }
        return carImageResponseList;
    }
}
