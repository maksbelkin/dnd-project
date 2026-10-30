package com.example.WebService.controller;

import com.example.WebService.client.AuthClient;
import com.example.WebService.client.GameClient;
import com.example.WebService.model.dto.GameApplicationDto;
import com.example.WebService.model.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class UserProfileController {

    private final AuthClient authClient;
    private final GameClient gameClient;

    @Autowired
    public UserProfileController(AuthClient authClient, GameClient gameClient) {
        this.authClient = authClient;
        this.gameClient = gameClient;
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/authorization";
        }

        UserDto user = authClient.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        model.addAttribute("user", user);


        try {
            List<GameApplicationDto> applications = gameClient.getMyApplications(userId);
            model.addAttribute("applications", applications);
        } catch (Exception e) {
            model.addAttribute("applications", List.of());
        }

        return "dndProfile/dndProfile";
    }
}