package com.example.car_connect.service;


import com.amazonaws.services.s3.model.S3Object;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CarImageService {
    List<CarImageResponse> uploadCarImage(List<MultipartFile> images, UUID carId);
    byte[] downloadCarImage(UUID imageId);
    void deleteCarImage(UUID imageId);
    CarImage getCarImageData(UUID imageId);
}