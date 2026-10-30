package com.examplegame.GameService.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_applications")
public class GameApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Column(name = "player_id", nullable = false)
    private UUID playerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "game_name")
    private String gameName;
}