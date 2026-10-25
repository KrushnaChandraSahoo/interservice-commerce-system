package com.isc.orderservice.event;

public record OrderEvent(
        Long orderId,
        Long userId,
        Long productId,
        Integer quantity,
        Double amount) {
}

