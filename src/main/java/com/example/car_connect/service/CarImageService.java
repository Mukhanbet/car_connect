package com.example.car_connect.service;


import com.amazonaws.services.s3.model.S3Object;
import com.example.car_connect.model.domain.CarImage;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface CarImageService {
    CarImageResponse uploadCarImage(MultipartFile image);
    byte[] downloadCarImage(UUID imageId);
    void deleteCarImage(UUID imageId);
    CarImage getCarImageData(UUID imageId);
}
