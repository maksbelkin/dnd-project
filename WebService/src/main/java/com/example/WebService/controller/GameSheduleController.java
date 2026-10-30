package com.example.WebService.controller;

import com.example.WebService.client.GameClient;
import com.example.WebService.model.dto.GameApplicationDto;
import com.example.WebService.model.dto.GameCreateDto;
import com.example.WebService.model.dto.GameDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
public class GameSheduleController {

    private final GameClient gameClient;

    public GameSheduleController(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @GetMapping("/dnd-schedule")
    public String schedule(HttpSession session, Model model) {
        UUID currentUserId = (UUID) session.getAttribute("userId");

        List<GameDto> games = gameClient.getAllGames();
        model.addAttribute("games", games);
        model.addAttribute("currentUserId", currentUserId);

        if (currentUserId != null) {
            try {
                List<UUID> appliedGameIds = gameClient.getMyApplications(currentUserId)
                        .stream()
                        .map(GameApplicationDto::gameId)
                        .toList();
                model.addAttribute("appliedGameIds", appliedGameIds);
            } catch (Exception e) {
                model.addAttribute("appliedGameIds", List.of());
            }
        } else {
            model.addAttribute("appliedGameIds", List.of());
        }

        return "dndSheduleForge/dndSheduleForge";
    }

    @GetMapping("/games/edit/{id}")
    public String showEditForm(@PathVariable UUID id, HttpSession session, Model model) {
        UUID currentUserId = (UUID) session.getAttribute("userId");
        if (currentUserId == null) {
            return "redirect:/authorization";
        }

        GameDto game = gameClient.getAllGames().stream()
                .filter(g -> g.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Игра не найдена"));

        GameCreateDto dto = new GameCreateDto(
                game.name(),
                game.image(),
                game.date(),
                game.time(),
                game.type(),
                game.level(),
                game.description(),
                currentUserId
        );

        model.addAttribute("gameCreateDto", dto);
        model.addAttribute("gameId", id);
        return "dndPutGame/dndPutGame";
    }

    @PostMapping("/games/edit/{id}")
    public String editGame(@PathVariable UUID id,
                           @ModelAttribute GameCreateDto dto,
                           RedirectAttributes redirectAttributes) {
        try {
            gameClient.putGame(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Игра обновлена");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка обновления: " + e.getMessage());
        }
        return "redirect:/dnd-schedule";
    }

    @PostMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            gameClient.deleteGame(id);
            redirectAttributes.addFlashAttribute("successMessage", "Игра удалена");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка удаления");
        }
        return "redirect:/dnd-schedule";
    }

    @PostMapping("/games/apply/{id}")
    public String applyToGame(@PathVariable UUID id,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/authorization";
        }

        try {
            gameClient.applyToGame(id, userId);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Ссылка на игру отправлена на вашу почту");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/dnd-schedule";
    }

}