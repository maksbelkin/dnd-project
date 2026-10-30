package com.example.AuthService.model.dto;

import com.example.AuthService.model.entity.UserEntity;

public class UserRegistrationMapper {

    public static UserDto toDto(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getLogin(),
                entity.getRole(),
                entity.getEmail(),
                entity.getCreatedAt()
        );
    }


    public static UserEntity toEntity(UserRegistrationCreateDto dto) {
        return UserEntity.builder()
                .login(dto.login())
                .role(dto.role())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

}