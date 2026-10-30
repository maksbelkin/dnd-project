package com.example.AuthService.controller.api;

import com.example.AuthService.model.dto.UserRegistrationCreateDto;
import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.service.UserRegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRegistrationApiController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationApiController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserRegistrationCreateDto dto) {
        return userRegistrationService.createUser(dto);
    }
}
