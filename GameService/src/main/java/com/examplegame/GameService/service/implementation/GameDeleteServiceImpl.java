package com.examplegame.GameService.service.implementation;

import com.examplegame.GameService.repository.GameRepository;
import com.examplegame.GameService.repository.UserRepository;
import com.examplegame.GameService.service.GameDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameDeleteServiceImpl implements GameDeleteService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameDeleteServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void deleteGame(UUID id) {
        gameRepository.deleteById(id);
    }
}
