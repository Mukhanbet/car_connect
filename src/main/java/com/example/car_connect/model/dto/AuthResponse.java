package com.example.car_connect.model.dto;

import com.example.car_connect.model.enums.Role;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthResponse {
    private String token;
    private Role role;
}
