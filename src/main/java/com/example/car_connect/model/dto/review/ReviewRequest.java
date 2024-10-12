package com.example.car_connect.model.dto.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotBlank(message = "Comment cannot be empty or consist only of spaces")
    private String comment;
}
