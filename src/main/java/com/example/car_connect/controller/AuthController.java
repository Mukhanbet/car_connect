package com.example.car_connect.controller;

import com.example.car_connect.model.dto.auth.AuthResponse;
import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;
import com.example.car_connect.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/test")
    public String message() {
        return "It's working";
    }
}
