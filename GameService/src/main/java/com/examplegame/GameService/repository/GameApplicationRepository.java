package com.examplegame.GameService.repository;

import com.examplegame.GameService.model.entity.GameApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameApplicationRepository extends JpaRepository<GameApplicationEntity, UUID> {

    List<GameApplicationEntity> findAllByPlayerId(UUID playerId);

    boolean existsByGameIdAndPlayerId(UUID gameId, UUID playerId);
}