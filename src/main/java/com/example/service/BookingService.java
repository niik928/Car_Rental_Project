package com.example.service;

import com.example.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(
            Long userId,
            Long vehicleId,
            BookingDto bookingDto);

    BookingDto getBookingById(Long bookingId);

    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsByUser(Long userId);

    BookingDto cancelBooking(Long bookingId);

}
