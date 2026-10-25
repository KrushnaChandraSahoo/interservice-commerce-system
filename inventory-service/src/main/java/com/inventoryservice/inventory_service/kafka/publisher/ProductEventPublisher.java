package com.inventoryservice.inventory_service.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.inventoryservice.inventory_service.kafka.config.KafkaProductConfig;
import com.inventoryservice.inventory_service.kafka.event.ProductEvent;

@Component
public class ProductEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProductEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishProductCreated(ProductEvent event){
        String key = String.valueOf(event.productId());
        kafkaTemplate.send(KafkaProductConfig.PRODUCT_EVENTS,key,event);
    }
}
