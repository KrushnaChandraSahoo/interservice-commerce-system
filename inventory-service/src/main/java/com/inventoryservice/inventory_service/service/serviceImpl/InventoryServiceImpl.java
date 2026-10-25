package com.inventoryservice.inventory_service.service.serviceImpl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventoryservice.inventory_service.dto.request.ProductRequest;
import com.inventoryservice.inventory_service.dto.response.ProductResponse;
import com.inventoryservice.inventory_service.entity.Product;
import com.inventoryservice.inventory_service.exception.ProductNotFoundException;
import com.inventoryservice.inventory_service.kafka.event.ProductEvent;
import com.inventoryservice.inventory_service.kafka.publisher.ProductEventPublisher;
import com.inventoryservice.inventory_service.repository.ProductRepository;
import com.inventoryservice.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final ProductRepository productRepository;
    private final ProductEventPublisher productEvent;
    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = Product.builder()
                .productName(request.productName())
                .price(request.price())
                .quantity(request.quantity())
                .build();
        Product savedProduct = productRepository.save(product);
        productEvent.publishProductCreated(new ProductEvent(product.getProductId(),product.getProductName(),product.getPrice(),product.getQuantity(),Instant.now()));
        return mapToResponse(savedProduct);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Id Not Found"));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public ProductResponse reduceStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found"));
        if (product.getQuantity() < quantity) {
            throw new RuntimeException(
                    "Insufficient quantity");
        }
        product.setQuantity(
                product.getQuantity() - quantity);
        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

}
