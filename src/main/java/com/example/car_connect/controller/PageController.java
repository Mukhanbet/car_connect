package com.example.car_connect.controller;

import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PageController {
    @GetMapping("/auth")
    public String login(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth";
    }
}
