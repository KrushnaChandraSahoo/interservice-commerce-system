package com.notificationservice.event;
import java.time.Instant;
public record UserCreateEvent(
        Long userId,
        String name,
        String email,
        Instant occurredAt
) {

}
