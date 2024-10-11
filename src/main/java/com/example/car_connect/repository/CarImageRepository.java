package com.example.car_connect.repository;

import com.example.car_connect.model.domain.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, UUID> {
}
