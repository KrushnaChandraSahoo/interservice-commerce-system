package com.isc.paymentservice.service;

import java.util.List;

import com.isc.paymentservice.dto.request.PaymentRequest;
import com.isc.paymentservice.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getAllPayments();

    PaymentResponse getPaymentByOrderId(Long orderId);
}
