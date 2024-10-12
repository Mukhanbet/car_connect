package com.example.car_connect.controller;

import com.example.car_connect.model.dto.review.ReviewResponse;
import com.example.car_connect.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{carId}")
    public List<ReviewResponse> getAllReviews(
            @PathVariable UUID carId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return reviewService.getAllReviews(carId, page, size);
    }

    @PostMapping("/make/{carId}")
    public List<ReviewResponse> makeReview(
            @RequestHeader("Authorization") String token,
            @PathVariable UUID carId,
            @RequestParam(required = false) String comment,
            @RequestParam(required = false, defaultValue = "0") int rating
    ) {
        return reviewService.makeReview(token, carId, comment, rating);
    }
}
