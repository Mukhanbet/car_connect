package com.example.car_connect.model.dto.booking;

import com.example.car_connect.model.domain.Car;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponse {
    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private String status;
    private LocalDateTime createdAt;
    private Car car;
    private String tenantEmail;
}
