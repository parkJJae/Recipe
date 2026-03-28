package com.back.recipe.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 254, nullable = false)
    private String password;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    private LocalDateTime createdAt;
}
