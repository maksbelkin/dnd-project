package com.example.WebService.model.dto;

import com.example.WebService.model.enums.UserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String login,
        UserEnum role,
        String email,
        LocalDateTime createdAt
) {}