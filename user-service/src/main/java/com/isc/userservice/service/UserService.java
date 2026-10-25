package com.isc.userservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isc.userservice.dto.request.UserRequest;
import com.isc.userservice.dto.response.UserResponse;
import com.isc.userservice.dto.response.UserStatusResponse;

public interface UserService {
    
    public UserResponse addUser(UserRequest request);

    public List<UserResponse> getAllUsers();

    public UserResponse getUserById(Long id);

    public UserStatusResponse getAllUserStatus();

	public Page<UserResponse> getAllPageableUsers(Pageable pageable);

	public UserResponse updateUserById(Long id, UserRequest request);

}
