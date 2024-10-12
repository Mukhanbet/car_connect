package com.example.car_connect.controller;

import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.service.CarImageService;
import com.example.car_connect.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private CarService carService;

    @PostMapping("/register")
    public void register(@RequestBody CarRegisterRequest request) {
        carService.register(request);
    }
}
