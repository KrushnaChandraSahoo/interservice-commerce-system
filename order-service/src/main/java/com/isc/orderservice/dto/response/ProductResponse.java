package com.isc.orderservice.dto.response;

public record ProductResponse(
        Long productId,
        String productName,
        Double price,
        Integer quantity) {
}
