package com.example.AuthService.model.entity;

import com.example.AuthService.model.enums.UserEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "login")
    @NotBlank(message = "Логин обязателен для заполнения")
    @Size(min = 3, max = 30, message = "Логин должен содержать от 3 до 30 символов")
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserEnum role;

    @Column(name = "email")
    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный формат email")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Пароль обязателен для заполнения")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
