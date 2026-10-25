package com.isc.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.userservice.dto.request.UserRequest;
import com.isc.userservice.dto.response.UserResponse;
import com.isc.userservice.dto.response.UserStatusResponse;
import com.isc.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse addUser(@RequestBody UserRequest request) {
        return userService.addUser(request);
    }
    
    @GetMapping("/all")
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }
    //Paginated end points
    @GetMapping
    public Page<UserResponse> getAllUser(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return userService.getAllPageableUsers(pageable);
    }
    
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUserById(@PathVariable Long id,@RequestBody UserRequest request) {
        return userService.updateUserById(id,request);
    }
    
    @GetMapping("/status")
    public UserStatusResponse getAllUserStatus(){
        return userService.getAllUserStatus();
    }
}
