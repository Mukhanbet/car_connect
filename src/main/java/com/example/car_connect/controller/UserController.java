package com.example.car_connect.controller;

import com.example.car_connect.model.dto.user.OwnerResponse;
import com.example.car_connect.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final MyUserService userService;

    @PutMapping
    public OwnerResponse uploadImage(
            @RequestPart MultipartFile file,
            @RequestHeader("Authorization") String token
    ) {
        return userService.uploadImage(file, token);
    }
}
