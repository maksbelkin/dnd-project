package com.example.AuthService.service.implementation;

import com.example.AuthService.model.dto.UserRegistrationCreateDto;
import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.model.dto.UserRegistrationMapper;
import com.example.AuthService.model.entity.UserEntity;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.utils.PasswordHasher;
import com.example.AuthService.service.UserRegistrationService;
import com.example.AuthService.validation.UserRegistrationValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final UserRegistrationValidation userRegistrationValidation = new UserRegistrationValidation();

    @Autowired
    public UserRegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserRegistrationCreateDto dto) {
        UserEntity newUser = UserRegistrationMapper.toEntity(dto);
        newUser.setCreatedAt(LocalDateTime.now());

        newUser.setPassword(PasswordHasher.hash(dto.password()));

        if (userRegistrationValidation.isUnique(newUser, userRepository)) {
            UserEntity saved = userRepository.save(newUser);
            return UserRegistrationMapper.toDto(saved);
        }
        throw new RuntimeException("Логин или Email заняты");
    }
}