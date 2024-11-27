package com.example.car_connect.service;


import com.example.car_connect.model.domain.Image;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.image.CarFonImageResponse;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    CarFonImageResponse uploadCarImage(List<MultipartFile> images, MultipartFile fonImage, UUID carId);
    List<CarImageResponse> getImages(UUID carId, int page, int size);
    byte[] downloadCarImage(String fileName);
    void deleteCarImage(UUID imageId);
    Image uploadOwnerImage(MultipartFile file, User owner);
}
