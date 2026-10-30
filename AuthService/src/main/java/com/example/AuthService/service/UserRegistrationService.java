package com.example.AuthService.service;

import com.example.AuthService.model.dto.UserRegistrationCreateDto;
import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.model.entity.UserEntity;

public interface UserRegistrationService {

    UserDto createUser(UserRegistrationCreateDto dto);
}