package com.example.AuthService.service;

import com.example.AuthService.model.dto.UserAuthorizationCreateDto;
import com.example.AuthService.model.dto.UserDto;

public interface UserAuthorizationService {

    UserDto authUser(UserAuthorizationCreateDto dto);

}
