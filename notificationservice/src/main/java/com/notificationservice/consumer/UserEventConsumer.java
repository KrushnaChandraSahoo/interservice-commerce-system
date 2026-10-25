package com.notificationservice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.notificationservice.event.UserCreateEvent;
import com.notificationservice.service.EmailService;

@Component
public class UserEventConsumer {
    @Autowired
    private EmailService emailService;

    public UserEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-service-group", concurrency = "3")
    public void handleUserCreated(@Payload UserCreateEvent event, Acknowledgment ack) {

        // 1. Send welcome email
        sendWelcomeEmail(event);

        // 3. Commit offset only after both succeed
        ack.acknowledge();
    }

    private void sendWelcomeEmail(UserCreateEvent event) {
        String body = "EMAIL sent to " + event.email() + " | Welcome " + event.name();
        System.out.println(body);
        emailService.sendNotificationEmail(event.email(), "Welcome" + event.name(), body);
    }
}
