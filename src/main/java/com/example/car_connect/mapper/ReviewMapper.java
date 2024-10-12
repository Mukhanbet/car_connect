package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.Review;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.review.ReviewRequest;
import com.example.car_connect.model.dto.review.ReviewResponse;

import java.util.List;

public interface ReviewMapper {
    ReviewResponse toResponse(Review review);
    List<ReviewResponse> toResponseList(List<Review> reviews);
    Review toReview(String comment, int rating, Car car, User reviewer);
}
