package com.isc.orderservice.dto.response;

public record PaymentResponse(
        Long paymentId,
        Long orderId,
        Double amount,
        String status) {
}