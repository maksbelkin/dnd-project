package com.example.WebService.controller;

import com.example.WebService.client.GameClient;
import com.example.WebService.model.dto.GameCreateDto;
import com.example.WebService.model.dto.UserRegistrationCreateDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

@Controller
public class GameCreateController {

    private final GameClient gameClient;

    @Autowired
    public GameCreateController(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @GetMapping("/dnd-create-game")
    public String showCreateGameForm(HttpSession session, Model model) {
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/authorization";
        }

        GameCreateDto dto = new GameCreateDto(
                null, null, null, null, null, null, null, userId
        );
        model.addAttribute("gameCreateDto", dto);

        return "dndCreateGame/dndCreateGame";
    }

    @PostMapping("/create-game")
    public String createGame(@Valid @ModelAttribute("gameCreateDto") GameCreateDto dto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "dndCreateGame/dndCreateGame";
        }
        try {
            gameClient.createGame(dto);
            return "redirect:/dnd-schedule";
        } catch (RestClientException e) {
            model.addAttribute("errorMessage", "Ошибка при создании игры");
            return "dndCreateGame/dndCreateGame";
        }
    }
}
