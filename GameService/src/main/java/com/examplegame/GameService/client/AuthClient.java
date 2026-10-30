package com.examplegame.GameService.client;

import com.examplegame.GameService.model.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthClient {

    private final RestClient authRestClient;

    public AuthClient(RestClient authRestClient) {
        this.authRestClient = authRestClient;
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
