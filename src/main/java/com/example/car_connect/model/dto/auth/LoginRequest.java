package com.example.car_connect.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password cannot be empty or consist only of spaces")
    @Size(min = 2, max = 14, message = "Password length must be from 2 to 14 characters")
    private String password;
}
