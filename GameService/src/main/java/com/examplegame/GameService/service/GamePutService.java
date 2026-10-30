package com.examplegame.GameService.service;

import com.examplegame.GameService.model.dto.GameCreateDto;
import com.examplegame.GameService.model.dto.GameDto;

import java.util.UUID;

public interface GamePutService {

    GameDto putGame(UUID gameId, GameCreateDto dto);

}
