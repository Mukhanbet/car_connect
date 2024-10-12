package com.example.car_connect.mapper.impl;

import com.example.car_connect.mapper.BookingMapper;
import com.example.car_connect.model.domain.Booking;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.booking.BookingRequest;
import com.example.car_connect.model.dto.booking.BookingResponse;
import com.example.car_connect.model.enums.BookingStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingMapperImpl implements BookingMapper {
    @Override
    public BookingResponse toBookingResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.getId());
        bookingResponse.setStartDate(booking.getStartDate());
        bookingResponse.setEndDate(booking.getEndDate());
        bookingResponse.setPrice(booking.getPrice());
        bookingResponse.setStatus(booking.getStatus().name());
        bookingResponse.setCreatedAt(booking.getCreatedAt());
        bookingResponse.setCar(booking.getCar());
        bookingResponse.setTenantEmail(booking.getTenant().getEmail());
        return bookingResponse;
    }

    @Override
    public List<BookingResponse> toBookingResponses(List<Booking> bookings) {
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingResponses.add(toBookingResponse(booking));
        }
        return bookingResponses;
    }

    @Override
    public Booking toBooking(BookingRequest request, Car car, User tenant) {
        Booking booking = new Booking();
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setStatus(BookingStatus.ACTIVE);
        booking.setPrice(request.getPrice());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setCar(car);
        booking.setTenant(tenant);
        return booking;
    }
}
