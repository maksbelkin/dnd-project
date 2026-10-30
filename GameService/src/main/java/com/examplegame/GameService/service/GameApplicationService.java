package com.examplegame.GameService.service;

import com.examplegame.GameService.model.dto.GameApplicationDto;

import java.util.List;
import java.util.UUID;

public interface GameApplicationService {
    GameApplicationDto applyToGame(UUID gameId, UUID playerId);
    List<GameApplicationDto> getApplicationsByPlayer(UUID playerId);
}