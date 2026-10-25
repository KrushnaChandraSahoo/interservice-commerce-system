package com.isc.userservice.dto.response;
import lombok.Builder;

@Builder 
public record UserResponse(
        long id,
        String name,
        String email,
        boolean status) {
}
