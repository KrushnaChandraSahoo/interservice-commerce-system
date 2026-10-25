package com.inventoryservice.inventory_service.kafka.config;

import java.beans.BeanProperty;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProductConfig {
    public static final String PRODUCT_EVENTS = "product-events";

    @Bean
    public NewTopic productEventTopic() {
        return TopicBuilder.name(PRODUCT_EVENTS)
                .partitions(3)
                .replicas(1)
                .build();
    }

}
