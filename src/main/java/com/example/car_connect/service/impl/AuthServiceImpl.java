package com.example.car_connect.service.impl;

import com.example.car_connect.config.JwtService;
import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.AuthMapper;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.AuthResponse;
import com.example.car_connect.model.dto.LoginRequest;
import com.example.car_connect.model.dto.RegisterRequest;
import com.example.car_connect.repository.UserRepository;
import com.example.car_connect.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("Email already exists!", HttpStatus.FOUND);
        }
        return mapper.toAuthResponse(userRepository.save(mapper.toUser(request)));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return mapper.toAuthResponse(userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new CustomException("Email not found!", HttpStatus.NOT_FOUND)));
    }

    @Override
    public User getUserFromToken(String token) {
        token = token.substring(7);
        String userEmail = jwtService.getUserEmailFromToken(token);
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("Email not found!", HttpStatus.NOT_FOUND));
    }
}
