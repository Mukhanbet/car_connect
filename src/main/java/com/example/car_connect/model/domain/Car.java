package com.example.car_connect.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
    private String transmission;
    private double mileage;
    private String color;
    private int year;
    private double price;
    private String location;
    private LocalDate availableFrom;
    private double rating;
    private String description;

    @OneToMany(mappedBy = "carDetail")
    private List<CarImage> images;

    @OneToOne
    @JoinColumn
    private CarImage fonImage;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn
    private User owner;
}
