package com.isc.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isc.orderservice.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long>{

}
