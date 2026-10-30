package com.example.AuthService.service;

import com.example.AuthService.model.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInformationService {

    Optional<UserDto> getUserById(UUID id);

    List<UserDto> getUsers();

}
