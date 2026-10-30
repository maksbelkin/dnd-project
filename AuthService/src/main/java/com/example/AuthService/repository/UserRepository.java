package com.example.AuthService.repository;

import com.example.AuthService.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByLoginAndEmail(String login, String email);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
