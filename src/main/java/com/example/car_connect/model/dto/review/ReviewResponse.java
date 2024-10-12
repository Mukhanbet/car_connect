package com.example.car_connect.model.dto.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private String comment;
    private double rating;
    private String reviewer;
    private LocalDateTime createdAt;
}
