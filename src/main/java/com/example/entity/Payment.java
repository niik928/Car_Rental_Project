package com.example.entity;

import com.example.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Double amount;
    @Column(name = "payment_method")
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status =PaymentStatus.PENDING;

    @Column(name = "transaction_id")
    private String transactionId;
    LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
