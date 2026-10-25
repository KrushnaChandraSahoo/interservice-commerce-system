package com.isc.paymentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isc.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
        Optional<Payment> findByOrderId(Long orderId);
}
