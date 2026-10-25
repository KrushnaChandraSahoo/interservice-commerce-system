package com.isc.orderservice.dto.request;

public record PaymentRequest(
                Long orderId,
                double amount) {
}
