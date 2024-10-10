package com.example.car_connect.mapper.impl;

import com.example.car_connect.config.JwtService;
import com.example.car_connect.mapper.AuthMapper;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.AuthResponse;
import com.example.car_connect.model.dto.RegisterRequest;
import com.example.car_connect.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthMapperImpl implements AuthMapper {
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Override
    public AuthResponse toAuthResponse(User user) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setRole(user.getRole());
        authResponse.setToken(jwtService.generateToken(user));
        return authResponse;
    }

    @Override
    public User toUser(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(Role.valueOf(request.getRole()));
        return user;
    }
}
