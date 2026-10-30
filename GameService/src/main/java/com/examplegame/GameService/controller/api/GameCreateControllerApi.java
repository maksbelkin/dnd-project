package com.examplegame.GameService.controller.api;

import com.examplegame.GameService.model.dto.GameCreateDto;
import com.examplegame.GameService.model.dto.GameDto;
import com.examplegame.GameService.service.GameCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/create/game")
public class GameCreateControllerApi {

    private final GameCreateService gameCreateService;

    @Autowired
    public GameCreateControllerApi(GameCreateService gameCreateService) {
        this.gameCreateService = gameCreateService;
    }

    @PostMapping
    public GameDto addGame(@RequestBody @Valid GameCreateDto dto) {
        return gameCreateService.createGame(dto);
    }
}
