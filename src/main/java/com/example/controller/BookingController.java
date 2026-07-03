package com.example.controller;

import com.example.dto.BookingDto;
import com.example.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    //Get All Booking
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<BookingDto> getAllBookings(){
        return bookingService.getAllBookings();
    }

    //Create booking
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/{userId}/vehicle/{vehicleId}")
    public BookingDto createBooking(
            @PathVariable Long userId,
            @PathVariable Long vehicleId,
            @RequestBody BookingDto bookingDto
    ){
        return bookingService.createBooking(userId,vehicleId,bookingDto);
    }

    //Cancel Booking
    @PutMapping("/{bookingId}/cancel")
    public BookingDto cancelBooking(@PathVariable Long bookingId){
        return bookingService.cancelBooking(bookingId);
    }

    //Get User Booking
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public List<BookingDto> getBookingsByUser(@PathVariable Long userId){
        return bookingService.getBookingsByUser(userId);

    }

    //Get By Booking Id
    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@PathVariable Long bookingId){
        return bookingService.getBookingById(bookingId);
    }
}
