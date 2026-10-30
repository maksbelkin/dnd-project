package com.examplegame.GameService.service.implementation;

import com.examplegame.GameService.client.AuthClient;
import com.examplegame.GameService.model.dto.GameCreateDto;
import com.examplegame.GameService.model.dto.GameDto;
import com.examplegame.GameService.model.dto.GameMapper;
import com.examplegame.GameService.model.dto.UserDto;
import com.examplegame.GameService.model.entity.GameEntity;
import com.examplegame.GameService.model.entity.UserEntity;
import com.examplegame.GameService.model.enums.UserEnum;
import com.examplegame.GameService.repository.GameRepository;
import com.examplegame.GameService.repository.UserRepository;
import com.examplegame.GameService.service.GameCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GameCreateServiceImpl implements GameCreateService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final AuthClient authClient;

    @Autowired
    public GameCreateServiceImpl(GameRepository gameRepository,
                                 UserRepository userRepository,
                                 AuthClient authClient) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.authClient = authClient;
    }


    @Override
    @Transactional
    public GameDto createGame(GameCreateDto dto) {

        // 1. Ищем пользователя в локальной БД GameService
        UserEntity master = userRepository.findById(dto.userId())
                .orElseGet(() -> {
                    // 2. Если нет — идём в AuthService
                    UserDto authUser = authClient.getUserById(dto.userId())
                            .orElseThrow(() -> new RuntimeException("Пользователь не найден в AuthService"));

                    // 3. Создаем локальную копию
                    UserEntity copy = new UserEntity();
                    copy.setId(authUser.id());
                    copy.setLogin(authUser.login());
                    copy.setRole(authUser.role());
                    copy.setEmail(authUser.email());
                    return userRepository.save(copy);
                });

        // 4. Проверяем роль
        if (master.getRole() != UserEnum.MASTER) {
            throw new RuntimeException("Только мастер может создавать игры");
        }

        // 5. Создаем игру
        GameEntity newGame = GameMapper.toEntity(dto);
        newGame.setMaster(master);
        newGame.setCreatedAt(LocalDateTime.now());

        // 6. Сохраняем
        GameEntity saved = gameRepository.save(newGame);
        return GameMapper.toDto(saved);
    }
}
