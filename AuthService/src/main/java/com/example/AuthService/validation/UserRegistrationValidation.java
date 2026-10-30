package com.example.AuthService.validation;

import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.model.dto.UserRegistrationCreateDto;
import com.example.AuthService.model.dto.UserRegistrationMapper;
import com.example.AuthService.model.entity.UserEntity;
import com.example.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class UserRegistrationValidation {


    public boolean isUnique(UserEntity newUser, UserRepository userRepository) {
        return !userRepository.existsByLogin(newUser.getLogin())
                && !userRepository.existsByEmail(newUser.getEmail());
    }
}
