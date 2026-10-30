package com.examplegame.GameService.controller.api;

import com.examplegame.GameService.model.dto.GameCreateDto;
import com.examplegame.GameService.model.dto.GameDto;
import com.examplegame.GameService.service.implementation.GamePutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games")
public class GamePutControllerApi {

    private final GamePutServiceImpl gamePutService;

    @Autowired
    public GamePutControllerApi(GamePutServiceImpl gamePutService) {
        this.gamePutService = gamePutService;
    }

    @PutMapping("/{id}")   // ← было без /{id}
    public GameDto putGame(@PathVariable UUID id, @RequestBody GameCreateDto dto) {
        return gamePutService.putGame(id, dto);
    }
}