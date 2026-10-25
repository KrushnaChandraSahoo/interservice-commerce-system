package com.isc.userservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.isc.userservice.config.KafkaTopicConfig;
import com.isc.userservice.event.UserCreateEvent;

@Component
public class UserEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
     public UserEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishUserCreated(UserCreateEvent event){
        String key = String.valueOf(event.userId());
        kafkaTemplate.send(KafkaTopicConfig.USER_EVENTS,key,event);
    }
}
