package com.inventoryservice.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryservice.inventory_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
