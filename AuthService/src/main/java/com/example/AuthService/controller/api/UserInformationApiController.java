package com.example.AuthService.controller.api;

import com.example.AuthService.model.dto.UserDto;
import com.example.AuthService.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserInformationApiController {

    private final UserInformationService userInformationService;

    @Autowired
    public UserInformationApiController(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userInformationService.getUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.of(userInformationService.getUserById(userId));
    }

}
