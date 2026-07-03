package com.example.dto;

import com.example.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    private Long id;

    private String pickupLocation;

    private String dropLocation;

    private LocalDate pickupDate;

    private LocalDate returnDate;

    private Integer totalDays;

    private Double totalAmount;

    private BookingStatus status;

}
