package com.example.serviceImpl;

import com.example.dto.PaymentDto;
import com.example.entity.Booking;
import com.example.entity.Payment;
import com.example.enums.PaymentStatus;
import com.example.repository.BookingRepository;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    public PaymentDto createPayment(Long bookingId, PaymentDto paymentDto) {

        //System.out.println(paymentDto.getPaymentMethod());
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException("Booking Not Found"));

        Payment payment = new Payment();
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setTransactionId(UUID.randomUUID().toString());

        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setBooking(booking);

        Payment  savedPayment = paymentRepository.save(payment);

        return modelMapper.map(savedPayment ,PaymentDto.class);
    }

    @Override
    public PaymentDto getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));

        return modelMapper.map(payment, PaymentDto.class);
    }

    @Override
    public PaymentDto updatePaymentStatus(Long paymentId,
                                          PaymentDto paymentDto) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));

        payment.setStatus(
                PaymentStatus.valueOf(paymentDto.getStatus().toUpperCase())
        );

        Payment updatedPayment = paymentRepository.save(payment);

        return modelMapper.map(updatedPayment, PaymentDto.class);
    }

    @Override
    public List<PaymentDto> getAllPayments() {

        List<Payment> payments = paymentRepository.findAll();

        if (payments.isEmpty()) {
            throw new RuntimeException("No Payments Found");
        }

        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .toList();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void deletePayment(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));

        Booking booking = payment.getBooking();

        if (booking != null) {
            booking.setPayment(null);
        }

        paymentRepository.delete(payment);
        paymentRepository.flush();
    }


}
