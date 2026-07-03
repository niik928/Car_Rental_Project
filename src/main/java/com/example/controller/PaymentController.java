package com.example.controller;

import com.example.dto.PaymentDto;
import com.example.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    //Create Payment
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/booking/{bookingId}")
    public PaymentDto createPayment(@PathVariable Long bookingId, @RequestBody PaymentDto paymentDto){
        return paymentService.createPayment(bookingId , paymentDto);
    }

    //delete payment
    @DeleteMapping("/{paymentId}")
  public String deletePayment(@PathVariable Long paymentId){
        paymentService.deletePayment(paymentId);
        return "Payment deleted Successfully";
  }


  //get All Payment
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public List<PaymentDto> getAllPayments() {
      return paymentService.getAllPayments();
  }

  //get Payment By Id
  @GetMapping("/{paymentId}")
  public PaymentDto getPaymentById(@PathVariable Long paymentId) {

      return paymentService.getPaymentById(paymentId);
  }

  //update payment Status
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{paymentId}/status")
  public PaymentDto updatePaymentStatus(
          @PathVariable Long paymentId,
          @RequestBody PaymentDto paymentDto) {

      return paymentService.updatePaymentStatus(paymentId, paymentDto);
  }
}
