package com.examplegame.GameService.controller.api;

import com.examplegame.GameService.model.dto.GameApplicationDto;
import com.examplegame.GameService.service.GameApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/applications")
public class GameApplicationApiController {

    private final GameApplicationService applicationService;

    public GameApplicationApiController(GameApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/{gameId}")
    public GameApplicationDto apply(@PathVariable UUID gameId, @RequestParam UUID playerId) {
        return applicationService.applyToGame(gameId, playerId);
    }

    @GetMapping("/player/{playerId}")
    public List<GameApplicationDto> getByPlayer(@PathVariable UUID playerId) {
        return applicationService.getApplicationsByPlayer(playerId);
    }
}