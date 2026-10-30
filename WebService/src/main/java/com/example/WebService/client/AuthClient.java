package com.example.WebService.client;

import com.example.WebService.model.dto.UserAuthorizationCreateDto;
import com.example.WebService.model.dto.UserDto;
import com.example.WebService.model.dto.UserRegistrationCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthClient {
    private final RestClient authRestClient;

    @Autowired
    public AuthClient(RestClient authRestClient) {
        this.authRestClient = authRestClient;
    }

    public UserDto authUser(UserAuthorizationCreateDto dto) {
        return authRestClient.post()
                .uri("/api/v1/auth/user")
                .body(dto)
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto createUser(UserRegistrationCreateDto dto) {
        return authRestClient.post()
                .uri("/api/v1/users")
                .body(dto)
                .retrieve()
                .body(UserDto.class);
    }

    public Optional<UserDto> getUserById(UUID id) {
        try {
            UserDto user = authRestClient.get()
                    .uri("/api/v1/users/{id}", id)
                    .retrieve()
                    .body(UserDto.class);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}