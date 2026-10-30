package com.example.WebService.controller;

import com.example.WebService.client.AuthClient;
import com.example.WebService.model.dto.UserAuthorizationCreateDto;
import com.example.WebService.model.dto.UserDto;
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

@Controller
public class UserAuthorizationController {

    private final AuthClient authClient;

    @Autowired
    public UserAuthorizationController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @GetMapping("/authorization")
    public String authUserForm(Model model) {
        model.addAttribute("userAuthorizationCreateDto",
                new UserAuthorizationCreateDto(null, null, null));
        return "dndAuthorization/dndAuthorization";
    }

    @PostMapping("/auth-user")
    public String authUser(@Valid @ModelAttribute("userAuthorizationCreateDto") UserAuthorizationCreateDto dto,
                           BindingResult bindingResult,
                           HttpSession session,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "dndAuthorization/dndAuthorization";
        }

        try {
            UserDto user = authClient.authUser(dto);
            session.setAttribute("userId", user.id());
            session.setAttribute("userLogin", user.login());
            session.setAttribute("userEmail", user.email());
            return "redirect:/profile";
        } catch (RestClientException e) {
            model.addAttribute("error", "Неверный логин, email или пароль");
            return "dndAuthorization/dndAuthorization";
        }
    }
}
