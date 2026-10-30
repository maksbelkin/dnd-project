package com.example.WebService.model.dto;

import com.example.WebService.model.enums.GameTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record GameDto(
        UUID id,
        String name,
        String image,
        String date,
        String time,
        GameTypeEnum type,
        String level,
        String description,
        LocalDateTime createdAt,
        UUID userId
) {
}
