package com.examplegame.GameService.model.dto;

import java.util.UUID;

public record GameApplicationDto(
        UUID id,
        UUID gameId,
        String gameName,
        UUID playerId
) {
}