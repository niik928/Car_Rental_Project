package com.example.entity;

import com.example.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String pickupLocation;
    private String dropLocation;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private Integer totalDays;
    private Double totalAmount;

 @Enumerated(EnumType.STRING)
 private BookingStatus status = BookingStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne(mappedBy = "booking" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
