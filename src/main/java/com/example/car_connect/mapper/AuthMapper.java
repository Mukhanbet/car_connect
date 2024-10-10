package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.AuthResponse;
import com.example.car_connect.model.dto.RegisterRequest;

public interface AuthMapper {
    AuthResponse toAuthResponse(User user);

    User toUser(RegisterRequest request);
}
