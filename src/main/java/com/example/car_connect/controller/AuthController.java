package com.example.car_connect.controller;

import com.example.car_connect.model.dto.auth.AuthResponse;
import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;
import com.example.car_connect.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return "redirect:/pages/auth";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request) {
        AuthResponse response = authService.login(request);
        return "redirect:/pages/auth";
    }
}