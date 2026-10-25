package com.inventoryservice.inventory_service.kafka.event;

import java.time.Instant;

public record ProductEvent(
        Long productId,
        String productName,
        Double price,
        Integer quantity,
        Instant occurredAt) {

}
