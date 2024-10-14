package com.example.car_connect.mapper;

import com.example.car_connect.model.domain.Booking;
import com.example.car_connect.model.domain.Car;
import com.example.car_connect.model.domain.User;
import com.example.car_connect.model.dto.booking.BookingRequest;
import com.example.car_connect.model.dto.booking.BookingResponse;

import java.util.List;

public interface BookingMapper {
    BookingResponse toBookingResponse(Booking booking);
    List<BookingResponse> toBookingResponses(List<Booking> bookings);
    Booking toBooking(BookingRequest request, Car car, User tenant);
}
