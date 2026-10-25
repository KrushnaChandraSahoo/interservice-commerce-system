package com.inventoryservice.inventory_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
    @NotBlank 
    String productName,
    Double price,
    Integer quantity
) {

}
