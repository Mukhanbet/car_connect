package com.example.car_connect.controller;

import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
