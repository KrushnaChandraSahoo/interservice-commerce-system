package com.isc.paymentservice.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isc.paymentservice.dto.request.PaymentRequest;
import com.isc.paymentservice.dto.response.PaymentResponse;
import com.isc.paymentservice.entity.Payment;
import com.isc.paymentservice.exception.PaymentNotFoundException;
import com.isc.paymentservice.exception.ResourceNotFoundException;
import com.isc.paymentservice.repository.PaymentRepository;
import com.isc.paymentservice.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .orderId(request.orderId())
                .amount(request.amount())
                .status("SUCCESS")
                .build();
        Payment savedPayment = paymentRepository.save(payment);
        return mapToResponse(savedPayment);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found"));
        return mapToResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException(
                        "Payment not found for order id: " + orderId));

        return mapToResponse(payment);
    }

    // @Override
    // public Optional<PaymentResponse> getPaymentByOrderId(Long orderId) {
    //     return paymentRepository.findByOrderId(orderId)
    //             .map(this::mapToResponse);
    // }
}
