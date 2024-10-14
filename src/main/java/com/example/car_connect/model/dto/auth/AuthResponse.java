package com.example.car_connect.model.dto.auth;

import com.example.car_connect.model.enums.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Role role;
}
