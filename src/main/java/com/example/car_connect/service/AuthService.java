package com.example.car_connect.service;

import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.auth.AuthResponse;
import com.example.car_connect.model.dto.auth.LoginRequest;
import com.example.car_connect.model.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    User getUserFromToken(String token);
}
