package com.examplegame.GameService.controller.api;

import com.examplegame.GameService.service.GameDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/delete/game")
public class GameDeleteControllerApi {

    private final GameDeleteService gameDeleteService;

    public GameDeleteControllerApi(GameDeleteService gameDeleteService) {
        this.gameDeleteService = gameDeleteService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        gameDeleteService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
