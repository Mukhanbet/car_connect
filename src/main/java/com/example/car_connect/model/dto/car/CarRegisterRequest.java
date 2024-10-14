package com.example.car_connect.model.dto.car;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarRegisterRequest {
    @Size(min = 2, max = 14, message = "Size: min = 2, max = 14")
    private String make;
    @Size(min = 2, max = 14, message = "Size: min = 2, max = 14")
    private String model;
    @Size(min = 2, max = 20, message = "Size: min = 2, max = 20")
    private String color;
    @Max(value = 2024, message = "The year cannot be greater than 2024")
    private int year;
    @NotBlank(message = "Price cannot be empty or consist only of spaces")
    private double price;
    @NotBlank(message = "Location cannot be empty or consist only of spaces")
    private String location;
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDate availableFrom;
    @Size(min = 2, max = 400, message = "Description length must be from 2 to 400 characters")
    private String description;
}
