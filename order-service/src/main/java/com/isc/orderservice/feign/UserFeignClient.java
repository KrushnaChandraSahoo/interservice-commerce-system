package com.isc.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.isc.orderservice.dto.response.UserResponse;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/user/{id}")
    UserResponse getUserById(@PathVariable Long id);
}
