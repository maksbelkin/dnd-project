package com.example.AuthService.service.implementation;

import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.model.dto.UserRegistrationMapper;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.service.UserInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserInformationServiceImpl implements UserInformationService {

    private final UserRepository userRepository;

    @Autowired
    public UserInformationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id).map(UserRegistrationMapper::toDto);
    }


    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserRegistrationMapper::toDto)
                .toList();
    }


}
