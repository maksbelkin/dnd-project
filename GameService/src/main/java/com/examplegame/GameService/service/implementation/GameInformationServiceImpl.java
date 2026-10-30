package com.examplegame.GameService.service.implementation;

import com.examplegame.GameService.model.dto.GameDto;
import com.examplegame.GameService.model.dto.GameMapper;
import com.examplegame.GameService.repository.GameRepository;
import com.examplegame.GameService.service.GameInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameInformationServiceImpl implements GameInformationService {


    private final GameRepository gameRepository;

    @Autowired
    public GameInformationServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<GameDto> getGameById(UUID id) {
        return gameRepository.findById(id).map(GameMapper::toDto);
    }

    @Override
    public List<GameDto> getGame() {
        return gameRepository.findAll()
                .stream()
                .map(GameMapper::toDto)
                .toList();
    }
}
