package com.isc.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.isc.orderservice.dto.request.PaymentRequest;
import com.isc.orderservice.dto.response.PaymentResponse;

//@FeignClient(name = "PAYMENT-SERVICE")
@FeignClient(name = "payment-service")
public interface PaymentFeignClient {

    @PostMapping("/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);

    @GetMapping ("/payments/orders/{id}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long id );
}
