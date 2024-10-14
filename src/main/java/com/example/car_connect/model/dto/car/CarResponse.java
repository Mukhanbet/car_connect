package com.example.car_connect.model.dto.car;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CarResponse {
    private String id;
    private String make;
    private String model;
    private Integer year;
    private Double price;
    private String location;
    private LocalDate availableFrom;
    private Double rating;
    private String description;
    private List<String> imagesPaths;
    private List<String> reviews;
}
