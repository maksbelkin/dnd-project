package com.example.WebService.controller;

import com.example.WebService.client.AuthClient;
import com.example.WebService.model.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class GameYourForgeController {

    private final AuthClient authClient;

    @Autowired
    public GameYourForgeController(AuthClient authClient) {
        this.authClient = authClient;
    }


    @GetMapping("/dnd-your-forge")
    public String yourForge(HttpSession session, Model model) {
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/authorization";
        }

        UserDto user = authClient.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        model.addAttribute("user", user);
        return "dndYourForge/dndYourForge";
    }


}
