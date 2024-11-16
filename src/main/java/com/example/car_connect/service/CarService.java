package com.example.car_connect.service;

import com.example.car_connect.model.dto.car.CarFilter;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;
import com.example.car_connect.model.dto.image.CarImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarResponseDetail register(CarRegisterRequest request, List<MultipartFile> images, MultipartFile fonImage, String token);
    List<CarResponse> search(CarFilter filter, int page, int size);
    CarResponseDetail getDetail(UUID id);
    List<CarResponse> getRelatedCars(CarFilter filter);
    List<CarResponse> all();
}
