package com.isc.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.isc.orderservice.dto.response.ProductResponse;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryFeignClient {

    @GetMapping("/inventory/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @PutMapping("/inventory/reduce/{id}/{qty}")
    ProductResponse reduceStock(@PathVariable Long id, @PathVariable Integer qty);
}
