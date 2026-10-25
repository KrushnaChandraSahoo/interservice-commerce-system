package com.inventoryservice.inventory_service.service;

import java.util.List;

import com.inventoryservice.inventory_service.dto.request.ProductRequest;
import com.inventoryservice.inventory_service.dto.response.ProductResponse;

public interface InventoryService {
 ProductResponse addProduct(ProductRequest request);
 ProductResponse getProductById(Long id);
 List<ProductResponse> getAllProducts();
 //Check Stock
 //ProductResponse getStockById(Long id);
 //Reduce Stock After Order
 ProductResponse reduceStock(Long id,Integer quantity);
}
