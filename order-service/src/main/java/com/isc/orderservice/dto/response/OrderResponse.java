package com.isc.orderservice.dto.response;

import lombok.Builder;

@Builder
public record OrderResponse(
        Long orderId,
        String customerName,
        String productName,
        Integer quantity,
        Double amount,
        String paymentStatus,
        String orderStatus) {
}