package com.example.repository;

import com.example.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment , Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Payment p WHERE p.id = :paymentId")
    void deletePaymentByIdCustom(Long paymentId);

    @Query("SELECT COALESCE(SUM(p.amount),0) FROM Payment p")
    Double getTotalRevenue();
}
