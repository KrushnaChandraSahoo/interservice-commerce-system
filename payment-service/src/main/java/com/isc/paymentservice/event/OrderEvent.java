package com.isc.paymentservice.event;

public record OrderEvent(
        Long orderId,
        Long userId,
        Long productId,
        Integer quantity,
        Double amount) {
}
