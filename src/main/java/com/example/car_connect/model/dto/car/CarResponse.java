package com.example.car_connect.model.dto.car;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CarResponse {
    private UUID id;
    private String make;
    private String model;
    private String transmission;
    private double mileage;
    private Integer year;
    private Double price;
    private String fonImage;
}
