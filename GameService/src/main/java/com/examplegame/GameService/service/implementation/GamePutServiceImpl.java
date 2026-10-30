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
import com.examplegame.GameService.service.GamePutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GamePutServiceImpl implements GamePutService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final AuthClient authClient;

    public GamePutServiceImpl(GameRepository gameRepository,
                              UserRepository userRepository,
                              AuthClient authClient) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.authClient = authClient;
    }

    @Override
    @Transactional
    public GameDto putGame(UUID gameId, GameCreateDto dto) {
        GameEntity game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Игра не найдена"));

        UserEntity editor = findOrSyncUser(dto.userId());

        if (editor.getRole() != UserEnum.MASTER) {
            throw new RuntimeException("Только мастер может редактировать игры");
        }

        if (game.getMaster() == null || !game.getMaster().getId().equals(editor.getId())) {
            throw new RuntimeException("Только создатель игры может её редактировать");
        }

        game.setName(dto.name());
        game.setImage(dto.image());
        game.setDate(dto.date());
        game.setTime(dto.time());
        game.setType(dto.type());
        game.setLevel(dto.level());
        game.setDescription(dto.description());
        game.setCreatedAt(LocalDateTime.now());

        return GameMapper.toDto(gameRepository.save(game));
    }

    private UserEntity findOrSyncUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseGet(() -> syncUserFromAuth(userId));
    }

    private UserEntity syncUserFromAuth(UUID userId) {
        UserDto authUser = authClient.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден в AuthService"));

        UserEntity entity = UserEntity.builder()
                .id(authUser.id())
                .login(authUser.login())
                .email(authUser.email())
                .role(authUser.role())
                .build();

        return userRepository.save(entity);
    }

}
