package com.examplegame.GameService.controller.api;

import com.examplegame.GameService.model.dto.GameDto;
import com.examplegame.GameService.service.GameInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games")
public class GameInformationControllerApi {

    private final GameInformationService gameInformationService;

    @Autowired
    public GameInformationControllerApi(GameInformationService gameInformationService) {
        this.gameInformationService = gameInformationService;
    }

    @GetMapping
    public List<GameDto> getGame() {
        return gameInformationService.getGame();
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDto> getGameById(@PathVariable UUID gameId) {
        return ResponseEntity.of(gameInformationService.getGameById(gameId));
    }


}
