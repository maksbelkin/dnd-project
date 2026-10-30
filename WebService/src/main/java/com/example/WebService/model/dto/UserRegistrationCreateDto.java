package com.example.WebService.model.dto;

import com.example.WebService.model.enums.UserEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegistrationCreateDto(

        @NotBlank(message = "Логин обязателен")
        @Size(min = 3, max = 30, message = "Логин от 3 до 30 символов")
        String login,

        @NotNull(message = "Выберите роль")
        UserEnum role,

        @NotBlank(message = "Email обязателен")
        @Email(message = "Некорректный email")
        String email,

        @NotBlank(message = "Пароль обязателен")
        @Size(min = 6, message = "Пароль минимум 6 символов")
        String password
) {}