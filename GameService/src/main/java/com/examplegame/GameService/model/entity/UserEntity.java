package com.examplegame.GameService.model.entity;

import com.examplegame.GameService.model.enums.UserEnum;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "id")
    private UUID id;

    @Column(name = "login")
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserEnum role;

    @Column(name = "email")
    private String email;

}