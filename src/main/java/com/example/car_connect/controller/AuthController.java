package com.example.car_connect.controller;

import com.example.car_connect.model.dto.auth.AuthResponse;
import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;
import com.example.car_connect.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, HttpServletResponse httpResponse) {
        AuthResponse response = authService.register(request);
        attachTokenIntoCookie(response.getToken(), httpResponse);
        return "redirect:/pages/home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, HttpServletResponse httpResponse) {
        AuthResponse response = authService.login(request);
        attachTokenIntoCookie(response.getToken(), httpResponse);
        return "redirect:/pages/home";
    }

    private void attachTokenIntoCookie(String token, HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("access_token", token);

        tokenCookie.setMaxAge(24 * 60 * 60);
        tokenCookie.setPath("/");

        response.addCookie(tokenCookie);
    }
}