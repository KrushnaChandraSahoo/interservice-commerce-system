package com.isc.orderservice.dto.response;

public record UserResponse(
        Long id,
        String name,
        String email) {
}
