package com.isc.paymentservice.dto.response;

import lombok.Builder;

@Builder
public record PaymentResponse(
        Long paymentId,
        Long orderId,
        Double amount,
        String status) {

}
