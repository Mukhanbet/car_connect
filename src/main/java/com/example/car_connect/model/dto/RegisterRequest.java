package com.example.car_connect.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class RegisterRequest {
    @Size(min = 2, max = 24, message = "Size: min = 2, max = 24")
    private String name;
    @Email
    private String email;
    @Size(min = 4, max = 8, message = "Size: min = 4, max = 8")
    private String password;
    private String phone;
    private String role;
}
