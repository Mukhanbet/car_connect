package com.example.car_connect.service.impl;

import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.ReviewMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.review.ReviewResponse;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.repository.ReviewRepository;
import com.example.car_connect.repository.UserRepository;
import com.example.car_connect.service.AuthService;
import com.example.car_connect.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final AuthService authService;
    private final CarRepository carRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewResponse> getAllReviews(UUID carId, int page, int size) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        return reviewMapper.toResponseList(reviewRepository.findByCar(car, PageRequest.of(page, size)));
    }

    @Override
    public List<ReviewResponse> makeReview(String token, UUID carId, String comment, int rating) {
        User reviewer = authService.getUserFromToken(token);
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        if ((comment == null || comment.isEmpty()) && (rating == 0)) {
            return getAllReviews(car.getId(), 0, 10);
        } else {
            reviewRepository.save(reviewMapper.toReview(comment, rating, car, reviewer));
            return getAllReviews(carId, 0, 10);
        }
    }
}
