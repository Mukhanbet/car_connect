package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.auth.AuthResponse;
import com.example.car_connect.model.dto.auth.RegisterRequest;

public interface AuthMapper {
    AuthResponse toAuthResponse(User user);

    User toUser(RegisterRequest request);
}
