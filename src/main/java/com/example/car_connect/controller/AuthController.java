package com.example.car_connect.controller;

import com.example.car_connect.model.dto.auth.AuthResponse;
import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;
import com.example.car_connect.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
