package com.example.car_connect.service.impl;

import com.example.car_connect.exception.CustomException;
import com.example.car_connect.mapper.BookingMapper;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.booking.BookingRequest;
import com.example.car_connect.model.dto.booking.BookingResponse;
import com.example.car_connect.model.enums.BookingStatus;
import com.example.car_connect.repository.BookingRepository;
import com.example.car_connect.repository.CarRepository;
import com.example.car_connect.service.AuthService;
import com.example.car_connect.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final AuthService authService;
    private final CarRepository carRepository;
    private final BookingMapper bookingMapper;

    @Override
    public List<BookingResponse> getAllBookings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingMapper.toBookingResponses(bookingRepository.findAll(pageable).stream().toList());
    }

    @Override
    public List<BookingResponse> getAllBookingsByStatus(String status, int page, int size) {
        return bookingMapper.toBookingResponses(bookingRepository.findAllByStatus(BookingStatus.valueOf(status.toUpperCase()), PageRequest.of(page, size)));
    }

    @Override
    public List<BookingResponse> getAllTenantsBooking(String token, int page, int size) {
        User tenant = authService.getUserFromToken(token);
        return bookingMapper.toBookingResponses(bookingRepository.findAllByTenant(tenant, PageRequest.of(page, size)));
    }

    @Override
    public BookingResponse getBookingById(UUID id) {
        return bookingMapper.toBookingResponse(bookingRepository.findById(id).orElseThrow(() -> new CustomException("Booking not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<BookingResponse> create(String token, UUID carId, BookingRequest request) {
        User tenant = authService.getUserFromToken(token);
        Car car = carRepository.findById(carId).orElseThrow(() -> new CustomException("Car not found", HttpStatus.NOT_FOUND));
        bookingRepository.save(bookingMapper.toBooking(request, car, tenant));
        return getAllTenantsBooking(token, 0, 10);
    }
}
