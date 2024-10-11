package com.example.car_connect.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "cars_tb")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String make;
    private String model;
    private String color;
    private int year;
    private String price;
    private String location;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private double rating;
    private String description;

    @OneToMany(mappedBy = "car")
    private List<CarImage> images;
}
