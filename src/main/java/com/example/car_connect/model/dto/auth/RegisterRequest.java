package com.example.car_connect.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class RegisterRequest {
    @Size(min = 2, max = 24, message = "Size: min = 2, max = 24")
    @NotBlank(message = "Name cannot be empty or consist only of spaces")
    private String name;
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password cannot be empty or consist ony of spaces")
    @Size(min = 4, max = 14, message = "Password length must be from 2 to 14 characters")
    private String password;
    @NotBlank(message = "Phone cannot be empty or consist only of spaces")
    private String phone;
    @NotBlank(message = "Role cannot be empty or consist only of spaces")
    private String role;
}
