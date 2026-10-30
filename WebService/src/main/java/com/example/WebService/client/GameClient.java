package com.example.WebService.client;

import com.example.WebService.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
public class GameClient {

    private final RestClient gameRestClient;

    @Autowired
    public GameClient(RestClient gameRestClient) {
        this.gameRestClient = gameRestClient;
    }

    public GameDto createGame(GameCreateDto dto) {
        return gameRestClient.post()
                .uri("/api/v1/create/game")
                .body(dto)
                .retrieve()
                .body(GameDto.class);
    }

    public GameDto putGame(UUID gameId, GameCreateDto dto) {
        return gameRestClient.put()
                .uri("/api/v1/games/{id}", gameId)
                .body(dto)
                .retrieve()
                .body(GameDto.class);
    }

    public List<GameDto> getAllGames() {
        return gameRestClient.get()
                .uri("/api/v1/games")
                .retrieve()
                .body(new ParameterizedTypeReference<List<GameDto>>() {});
    }

    public void deleteGame(UUID gameId) {
        gameRestClient.delete()
                .uri("/api/v1/delete/game/{id}", gameId)
                .retrieve()
                .toBodilessEntity();
    }

    public GameApplicationDto applyToGame(UUID gameId, UUID playerId) {
        return gameRestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/applications/{gameId}")
                        .queryParam("playerId", playerId)
                        .build(gameId))
                .retrieve()
                .body(GameApplicationDto.class);
    }

    public List<GameApplicationDto> getMyApplications(UUID playerId) {
        return gameRestClient.get()
                .uri("/api/v1/applications/player/{playerId}", playerId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GameApplicationDto>>() {});
    }
}
