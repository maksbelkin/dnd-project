package com.examplegame.GameService.model.dto;

import com.examplegame.GameService.model.enums.UserEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String login,
        UserEnum role,
        String email,
        LocalDateTime createdAt
) {}
