package com.example.car_connect.repository;

import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.Image;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findAllByCar(Car car, PageRequest pageRequest);
    Optional<Image> findByName(String name);
}
