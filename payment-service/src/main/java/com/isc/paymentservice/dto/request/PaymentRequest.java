package com.isc.paymentservice.dto.request;

public record PaymentRequest(
        Long orderId,
        Double amount) {

}
