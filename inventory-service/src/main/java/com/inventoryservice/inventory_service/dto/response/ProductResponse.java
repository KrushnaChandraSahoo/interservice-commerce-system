package com.inventoryservice.inventory_service.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ProductResponse(
        @NotBlank Long productId,
        @NotBlank String productName,
        @NotBlank Double price,
        @NotBlank Integer quantity) {

}
