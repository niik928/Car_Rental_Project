package com.example.dto;

import lombok.Data;

@Data
public class PaymentDto {

    private Long id;

    private Double amount;

    private String paymentMethod;

    private String status;
}
