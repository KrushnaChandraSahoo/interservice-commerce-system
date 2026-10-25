package com.isc.userservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.isc.userservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    long countByActiveTrue();
    long countByActiveFalse();
}
