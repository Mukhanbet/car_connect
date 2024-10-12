package com.example.car_connect.model.dto.booking;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @FutureOrPresent(message = "The start date must be in the present or future")
    private LocalDateTime startDate;
    @Future(message = "The end date must be in the future")
    private LocalDateTime endDate;
    @NotBlank(message = "The price cannot be empty")
    private double price;
}
