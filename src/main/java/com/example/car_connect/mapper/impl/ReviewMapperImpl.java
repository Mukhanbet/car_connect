package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.ReviewMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.Review;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.review.ReviewResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapperImpl implements ReviewMapper {
    @Override
    public ReviewResponse toResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setComment(review.getComment());
        response.setRating(review.getRating());
        response.setReviewer(review.getReviewer().getEmail());
        response.setCreatedAt(review.getCreatedAt());
        return response;
    }

    @Override
    public List<ReviewResponse> toResponseList(List<Review> reviews) {
        List<ReviewResponse> responseList = new ArrayList<>();
        for (Review review : reviews) {
            responseList.add(toResponse(review));
        }
        return responseList;
    }

    @Override
    public Review toReview(String comment, int rating, Car car, User reviewer) {
        Review review = new Review();
        if (comment != null && !comment.isEmpty()) {
            review.setComment(comment);
        }
        if (rating != 0) {
            review.setCountRatings(review.getCountRatings() + 1);
            review.setRating((review.getRating() + rating) / review.getCountRatings());
        }
        review.setCreatedAt(LocalDateTime.now());
        review.setCar(car);
        review.setReviewer(reviewer);
        return review;
    }
}
