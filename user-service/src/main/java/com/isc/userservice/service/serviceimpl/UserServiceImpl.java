package com.isc.userservice.service.serviceimpl;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isc.userservice.dto.request.UserRequest;
import com.isc.userservice.dto.response.UserResponse;
import com.isc.userservice.dto.response.UserStatusResponse;
import com.isc.userservice.entity.UserEntity;
import com.isc.userservice.event.UserCreateEvent;
import com.isc.userservice.exception.ResourceNotFoundException;
import com.isc.userservice.kafka.UserEventPublisher;
import com.isc.userservice.repository.UserRepository;
import com.isc.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
         private final UserEventPublisher userEventPublisher; 
        @Override
        public UserResponse addUser(UserRequest request) {
                UserEntity user = UserEntity.builder()
                                .name(request.name())
                                .email(request.email())
                                .active(request.status())
                                .build();
                UserEntity saveUser = userRepository.save(user);
                
                // NEW: tell the rest of the system a user was created
                userEventPublisher.publishUserCreated(new UserCreateEvent(
                                saveUser.getId(),
                                saveUser.getName(),
                                saveUser.getEmail(),
                                Instant.now()));


                return UserResponse.builder()
                                .id(saveUser.getId())
                                .name(saveUser.getName())
                                .email(saveUser.getEmail())
                                .status(saveUser.isActive())
                                .build();
        }

        @Override
        public List<UserResponse> getAllUsers() {
                return userRepository.findAll()
                                .stream().map(user -> UserResponse.builder()
                                                .id(user.getId())
                                                .name(user.getName())
                                                .email(user.getEmail())
                                                .status(user.isActive())
                                                .build())
                                .toList();
        }

        @Override
        public UserResponse getUserById(Long id) {
                UserEntity user = userRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
                return UserResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .name(user.getName())
                                .status(user.isActive())
                                .build();
        }

        @Override
        public UserStatusResponse getAllUserStatus() {
                long totalCount = userRepository.count();
                long activeCount = userRepository.countByActiveTrue();
                long inActiveCount = userRepository.countByActiveFalse();
                return UserStatusResponse.builder()
                                .totalCount(totalCount)
                                .activeCount(activeCount)
                                .inActiveCount(inActiveCount)
                                .build();
        }

        @Override
        public Page<UserResponse> getAllPageableUsers(Pageable pageable) {
                return userRepository.findAll(pageable)
                                .map(this::mapToResponse);
        }

        private UserResponse mapToResponse(UserEntity user) {
                return UserResponse.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .status(user.isActive())
                                .build();
        }

        @Override
        public UserResponse updateUserById(Long id, UserRequest request) {
                UserEntity user = userRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

                user.setEmail(request.email());
                user.setName(request.name());
                user.setActive(request.status());
                //UserEntity updatedUser = userRepository.save(user);
                return mapToResponse(userRepository.save(user));

        }
}
