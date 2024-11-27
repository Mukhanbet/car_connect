package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Image;
import com.example.car_connect.model.dto.image.CarFonImageResponse;
import com.example.car_connect.model.dto.image.CarImageResponse;

import java.util.List;

public interface ImageMapper {
    Image toImage(String fileName, String path);
    CarFonImageResponse toCarFonImageResponse(Image image, List<Image> images);
    CarImageResponse toCarImageResponse(Image image);
    List<CarImageResponse> toCarImageResponseList(List<Image> images);
}
