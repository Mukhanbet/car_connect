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
    private String fonImage;
}
