package com.isc.orderservice.dto.request;

public record OrderRequest(
        Long userId,
        Long productId,
        Integer quantity) {

}
