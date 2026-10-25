package com.isc.orderservice.service;

import java.util.List;

import com.isc.orderservice.dto.request.OrderRequest;
import com.isc.orderservice.dto.response.OrderResponse;

public interface OrderService {
    
    OrderResponse placeOrder(OrderRequest request);
    List<OrderResponse>getAllOrder();
}
