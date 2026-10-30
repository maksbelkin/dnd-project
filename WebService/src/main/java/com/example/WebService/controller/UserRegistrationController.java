package com.example.WebService.controller;

import com.example.WebService.client.AuthClient;
import com.example.WebService.model.dto.UserRegistrationCreateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRegistrationController {

    private final AuthClient authClient;

    @Autowired
    public UserRegistrationController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @GetMapping("/registration")
    public String addUserForm(Model model) {
        model.addAttribute("userRegistrationCreateDto",
                new UserRegistrationCreateDto(null, null, null, null));
        return "dndRegistration/dndRegistration";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid @ModelAttribute("userRegistrationCreateDto") UserRegistrationCreateDto dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "dndRegistration/dndRegistration";
        }

        try {
            authClient.createUser(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Регистрация прошла успешно. Войдите в систему.");
            return "redirect:/authorization";
        } catch (ResourceAccessException e) {
            model.addAttribute("errorMessage", "Сервис регистрации временно недоступен");
            return "dndRegistration/dndRegistration";
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().value() == 409) {
                model.addAttribute("errorMessage", "Логин или email уже заняты");
            } else {
                model.addAttribute("errorMessage", "Не удалось создать пользователя");
            }
            return "dndRegistration/dndRegistration";
        } catch (RestClientException e) {
            model.addAttribute("errorMessage", "Не удалось создать пользователя");
            return "dndRegistration/dndRegistration";
        }
    }
}