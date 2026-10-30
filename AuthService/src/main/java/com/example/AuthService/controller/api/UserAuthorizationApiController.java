package com.example.AuthService.controller.api;

import com.example.AuthService.model.dto.UserAuthorizationCreateDto;
import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.service.UserAuthorizationService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/api/v1/auth/user")
public class UserAuthorizationApiController {

    private final UserAuthorizationService userAuthorizationService;

    public UserAuthorizationApiController(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @PostMapping
    public UserDto authUser(@RequestBody UserAuthorizationCreateDto dto) {
        return userAuthorizationService.authUser(dto);
    }
}