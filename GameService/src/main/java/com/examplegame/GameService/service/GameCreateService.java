package com.examplegame.GameService.service;

import com.examplegame.GameService.model.dto.GameCreateDto;
import com.examplegame.GameService.model.dto.GameDto;

public interface GameCreateService {

    GameDto createGame(GameCreateDto dto);

}
