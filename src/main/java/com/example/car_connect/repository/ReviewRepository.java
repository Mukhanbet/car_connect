package com.example.car_connect.repository;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByCar(Car car, PageRequest pageRequest);
}
