package com.example.car_connect.repository;

import com.example.car_connect.model.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {
    @Query(
            value = "SELECT * FROM cars_tb " +
            "WHERE (:make IS NULL OR make LIKE CONCAT('%', :make, '%')) " +
            "OR (:model IS NULL OR model LIKE CONCAT('%', :model, '%')) " +
            "OR (:color IS NULL OR color LIKE CONCAT('%', :color, '%')) " +
            "OR (:year IS NULL OR year = :year) " +
            "OR (:price IS NULL OR price = :price) " +
            "OR (:location IS NULL OR location LIKE CONCAT('%', :location, '%')) " +
            "OR (:availableFrom IS NULL OR available_from <= :availableFrom) " +
            "OR (:rating IS NULL OR rating = :rating)",
            nativeQuery = true
    )
    List<Car> findAllRelatedCars(
            @Param("make") String make,
            @Param("model") String model,
            @Param("color") String color,
            @Param("year") Integer year,
            @Param("price") Double price,
            @Param("location") String location,
            @Param("availableFrom") LocalDate availableFrom,
            @Param("rating") Double rating
    );

}
