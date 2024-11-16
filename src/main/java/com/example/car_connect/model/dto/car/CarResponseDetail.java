package com.example.car_connect.model.dto.car;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CarResponseDetail {
    private UUID id;
    private String make;
    private String model;
    private String color;
    private int year;
    private Double price;
    private String location;
    private LocalDate availableFrom;
    private Double rating;
    private List<String> images;
    private String fonImage;
    private String description;
}
