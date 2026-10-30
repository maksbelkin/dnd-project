package com.example.AuthService.service.implementation;

import com.example.AuthService.model.dto.UserAuthorizationCreateDto;
import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.model.dto.UserRegistrationMapper;
import com.example.AuthService.model.entity.UserEntity;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.utils.PasswordHasher;
import com.example.AuthService.service.UserAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthorizationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto authUser(UserAuthorizationCreateDto dto) {

        UserEntity user = userRepository
                .findByLoginAndEmail(dto.login(), dto.email())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!PasswordHasher.verify(dto.password(), user.getPassword())) {
            throw new RuntimeException("Неверный пароль");
        }

        log.info("Успешная авторизация: {}", user.getLogin());
        return UserRegistrationMapper.toDto(user);
    }
}