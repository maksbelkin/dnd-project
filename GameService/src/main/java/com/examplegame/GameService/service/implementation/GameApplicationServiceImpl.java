package com.examplegame.GameService.service.implementation;

import com.examplegame.GameService.model.dto.GameApplicationDto;
import com.examplegame.GameService.model.entity.GameApplicationEntity;
import com.examplegame.GameService.model.entity.GameEntity;
import com.examplegame.GameService.repository.GameApplicationRepository;
import com.examplegame.GameService.repository.GameRepository;
import com.examplegame.GameService.service.GameApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GameApplicationServiceImpl implements GameApplicationService {

    private final GameApplicationRepository applicationRepository;
    private final GameRepository gameRepository;

    public GameApplicationServiceImpl(GameApplicationRepository applicationRepository,
                                      GameRepository gameRepository) {
        this.applicationRepository = applicationRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    @Transactional
    public GameApplicationDto applyToGame(UUID gameId, UUID playerId) {
        GameEntity game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Игра не найдена"));

        if (applicationRepository.existsByGameIdAndPlayerId(gameId, playerId)) {
            throw new RuntimeException("Вы уже записаны на эту игру");
        }

        GameApplicationEntity app = GameApplicationEntity.builder()
                .game(game)
                .playerId(playerId)
                .createdAt(LocalDateTime.now())
                .gameName(game.getName())
                .build();

        GameApplicationEntity saved = applicationRepository.save(app);

        return new GameApplicationDto(
                saved.getId(),
                game.getId(),
                game.getName(),
                saved.getPlayerId()
        );
    }

    @Override
    public List<GameApplicationDto> getApplicationsByPlayer(UUID playerId) {
        return applicationRepository.findAllByPlayerId(playerId).stream()
                .map(a -> new GameApplicationDto(
                        a.getId(),
                        a.getGame().getId(),
                        a.getGame().getName(),
                        a.getPlayerId()
                ))
                .toList();
    }
}