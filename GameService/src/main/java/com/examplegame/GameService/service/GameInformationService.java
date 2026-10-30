package com.examplegame.GameService.service;

import com.examplegame.GameService.model.dto.GameDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameInformationService {

    Optional<GameDto> getGameById(UUID id);

    List<GameDto> getGame();

}
