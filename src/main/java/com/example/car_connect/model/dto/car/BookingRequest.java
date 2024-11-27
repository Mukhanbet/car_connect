package com.example.car_connect.model.dto.car;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequest {
    private String pickupLocation;
    private String dropLocation;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private String specialRequest;
}
