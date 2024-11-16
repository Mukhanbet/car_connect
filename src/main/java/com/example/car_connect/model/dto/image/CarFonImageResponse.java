package com.example.car_connect.model.dto.image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarFonImageResponse extends CarImageResponse {
    private List<CarImageResponse> images;
}
