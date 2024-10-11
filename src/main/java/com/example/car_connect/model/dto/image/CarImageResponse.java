package com.example.car_connect.model.dto.image;

import lombok.Data;

import java.util.UUID;

@Data
public class CarImageResponse {
    private UUID id;
    private String path;
    private String name;
}
