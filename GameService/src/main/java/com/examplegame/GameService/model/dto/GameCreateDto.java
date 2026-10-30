package com.examplegame.GameService.model.dto;

import com.examplegame.GameService.model.enums.GameTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record GameCreateDto(
        @NotBlank(message = "Название игры обязательно")
        @Size(min = 3, max = 30, message = "Название игры от 3 до 30 символов")
        String name,

        String image,

        @NotBlank(message = "Дата игры обязательна")
        String date,

        @NotBlank(message = "Время игры обязательно")
        String time,

        @NotNull(message = "Выберите тип игры")
        GameTypeEnum type,

        String level,

        @Size(max = 300, message = "Описание максимум до 300-та символов")
        String description,

        @NotNull(message = "ID мастера обязателен")
        UUID userId
) {
}
