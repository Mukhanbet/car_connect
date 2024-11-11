package com.example.car_connect.controller;

import com.example.car_connect.model.dto.car.CarFilter;
import com.example.car_connect.model.dto.car.CarRegisterRequest;
import com.example.car_connect.model.dto.car.CarResponse;
import com.example.car_connect.model.dto.car.CarResponseDetail;
import com.example.car_connect.model.dto.image.CarImageResponse;
import com.example.car_connect.service.CarImageService;
import com.example.car_connect.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @GetMapping("/search")
//    public String search(
//            @ModelAttribute CarFilter filter,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10")int size,
//            Model model
//    ) {
//         List<CarResponse> carResponseList = carService.search(filter, page, size);
//         model.addAttribute("carResponseList", carResponseList);
//         return "index";
//    }

    @GetMapping("/{id}")
    public CarResponseDetail getDetail(@PathVariable UUID id) {
        return carService.getDetail(id);
    }
}
