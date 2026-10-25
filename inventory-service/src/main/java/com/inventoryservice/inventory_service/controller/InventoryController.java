package com.inventoryservice.inventory_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryservice.inventory_service.dto.request.ProductRequest;
import com.inventoryservice.inventory_service.dto.response.ProductResponse;
import com.inventoryservice.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ProductResponse addProduct(
            @RequestBody ProductRequest request) {
        return inventoryService.addProduct(
                request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Long id) {
        return inventoryService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @PutMapping("/reduce/{id}/{qty}")
    public ProductResponse reduceStock(
            @PathVariable Long id,
            @PathVariable Integer qty) {
        return inventoryService.reduceStock(id, qty);
    }
}
