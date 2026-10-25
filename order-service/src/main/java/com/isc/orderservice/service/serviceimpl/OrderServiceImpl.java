package com.isc.orderservice.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isc.orderservice.dto.request.OrderRequest;
import com.isc.orderservice.dto.request.PaymentRequest;
import com.isc.orderservice.dto.response.OrderResponse;
import com.isc.orderservice.dto.response.PaymentResponse;
import com.isc.orderservice.dto.response.ProductResponse;
import com.isc.orderservice.dto.response.UserResponse;
import com.isc.orderservice.entity.Orders;
import com.isc.orderservice.feign.InventoryFeignClient;
import com.isc.orderservice.feign.PaymentFeignClient;
import com.isc.orderservice.feign.UserFeignClient;
import com.isc.orderservice.repository.OrderRepository;
import com.isc.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserFeignClient userFeignClient;
    private final InventoryFeignClient inventoryFeignClient;
    private final PaymentFeignClient paymentFeignClient;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        UserResponse user = userFeignClient.getUserById(request.userId());
        ProductResponse product = inventoryFeignClient.getProductById(request.productId());

        if (product.quantity() < request.quantity()) {
            throw new RuntimeException("Insufficient Stock");
        }
        double totalAmount = product.price() * request.quantity();

        // PaymentRequest paymentRequest = new PaymentRequest();
        // paymentRequest.setAmount(totalAmount);
        // paymentRequest.setOrderId(0L);
        // PaymentRequest paymentRequest = new PaymentRequest(0L, totalAmount);
        // PaymentResponse payment = paymentFeignClient.processPayment(paymentRequest);

        inventoryFeignClient.reduceStock(request.productId(), request.quantity());
        Orders order = Orders.builder()
                .userId(request.userId())
                .productId(request.productId())
                .quantity(request.quantity())
                .amount(totalAmount)
                .orderStatus("PLACED")
                .build();
        Orders savedOrder = orderRepository.save(order);
        PaymentRequest paymentRequest = new PaymentRequest(savedOrder.getOrderId(), totalAmount);
        PaymentResponse payment = paymentFeignClient.processPayment(paymentRequest);
        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .customerName(user.name())
                .productName(product.productName())
                .amount(totalAmount)
                .paymentStatus(payment.status())
                .orderStatus(savedOrder.getOrderStatus())
                .build();
    }

    @Override
    public List<OrderResponse> getAllOrder() {

        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    UserResponse user = userFeignClient.getUserById(order.getUserId());
                    ProductResponse product = inventoryFeignClient.getProductById(order.getProductId());
                    PaymentResponse payment = paymentFeignClient.getPaymentByOrderId(order.getOrderId());

                    return OrderResponse.builder()
                            .orderId(order.getOrderId())
                            .customerName(user.name())
                            .productName(product.productName())
                            .quantity(order.getQuantity())
                            .amount(order.getAmount())
                            .paymentStatus(payment.status())
                            .build();
                })
                .toList();
    }
}
