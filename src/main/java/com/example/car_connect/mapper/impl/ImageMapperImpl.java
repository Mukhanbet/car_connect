package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.ImageMapper;
import com.example.car_connect.model.domain.Image;
import com.example.car_connect.model.dto.image.CarFonImageResponse;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMapperImpl implements ImageMapper {
    @Override
    public Image toImage(String fileName, String path) {
        Image image = new Image();
        image.setName(fileName);
        image.setPath(path);
        return image;
    }

    @Override
    public CarFonImageResponse toCarFonImageResponse(Image image, List<Image> images) {
        CarFonImageResponse response = new CarFonImageResponse();
        response.setId(image.getId());
        response.setName(image.getName());
        response.setPath(image.getPath());
        response.setImages(toCarImageResponseList(images));
        return response;
    }

    @Override
    public CarImageResponse toCarImageResponse(Image image) {
        CarImageResponse imageResponse = new CarImageResponse();
        imageResponse.setId(image.getId());
        imageResponse.setName(image.getName());
        imageResponse.setPath(image.getPath());
        return imageResponse;
    }

    @Override
    public List<CarImageResponse> toCarImageResponseList(List<Image> images) {
        List<CarImageResponse> carImageResponseList = new ArrayList<>();
        for (Image image : images) {
            carImageResponseList.add(toCarImageResponse(image));
        }
        return carImageResponseList;
    }
}
