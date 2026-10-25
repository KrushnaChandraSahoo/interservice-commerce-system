package com.isc.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name="user_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}
