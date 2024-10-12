package com.example.car_connect.controller;

import com.example.car_connect.model.dto.booking.BookingRequest;
import com.example.car_connect.model.dto.booking.BookingResponse;
import com.example.car_connect.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public List<BookingResponse> getAllBookings(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (status != null) {
            return bookingService.getAllBookingsByStatus(status, page, size);
        } else {
            return bookingService.getAllBookings(page, size);
        }
    }

    @GetMapping("/my")
    public List<BookingResponse> getAllTenantsBooking(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return bookingService.getAllTenantsBooking(token, page, size);
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable UUID id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/{carId}")
    public List<BookingResponse> create(
            @RequestHeader("Authorization") String token,
            @PathVariable UUID carId,
            @RequestBody BookingRequest request
    ) {
        return bookingService.create(token, carId, request);
    }
}
