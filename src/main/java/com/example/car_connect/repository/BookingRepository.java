package com.example.car_connect.repository;

import com.example.car_connect.model.domain.Booking;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.enums.BookingStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByCar(Car car, PageRequest pageRequest);
    List<Booking> findAllByTenant(User tenant, PageRequest pageRequest);
    List<Booking> findAllByStatus(BookingStatus status, PageRequest pageRequest);
}
