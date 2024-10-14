package com.example.car_connect.model.dto.car;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CarFilter {
    @Size(min = 2, max = 14, message = "Size: min = 2, max = 14")
    private String make;
    @Size(min = 2, max = 14, message = "Size: min = 2, max = 14")
    private String model;
    @Size(min = 2, max = 20, message = "Size: min = 2, max = 20")
    private String color;
    @Max(value = 2024, message = "The year cannot be greater than 2024")
    private Integer year;
    @NotBlank(message = "Price cannot be empty or consist only of spaces")
    private Double price;
    @NotBlank(message = "Location cannot be empty or consist only of spaces")
    private String location;
    @FutureOrPresent(message = "Start date must be in the present or future")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate availableFrom;
    private Double rating;
}
