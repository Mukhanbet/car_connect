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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private CarService carService;

    @PostMapping("/register")
    public CarResponseDetail register(
            @RequestPart CarRegisterRequest request,
            @RequestPart List<MultipartFile> images,
            @RequestPart MultipartFile fonImage,
            @RequestHeader("Authorization") String token
    ) {
        return carService.register(request, images, fonImage, token);
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

    @GetMapping
    public List<CarResponse> all() {
        return carService.all();
    }

    @GetMapping("/related_cars/{id}")
    public List<CarResponse> getRelatedCars(@PathVariable UUID id) {
        return carService.getRelatedCars(id);
    }
}
