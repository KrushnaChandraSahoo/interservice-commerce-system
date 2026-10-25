package com.isc.userservice.event;
import java.time.Instant;
public record UserCreateEvent(
                Long userId,
                String name,
                String email,
                Instant occurredAt
) {

}
