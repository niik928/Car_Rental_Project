package com.example.service;

import com.example.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

 PaymentDto createPayment(Long bookingId,PaymentDto paymentDto);

 PaymentDto getPaymentById(Long paymentId);

 PaymentDto updatePaymentStatus(Long paymentId,PaymentDto paymentDto);

 List<PaymentDto> getAllPayments();

 void deletePayment(Long paymentId);

}
