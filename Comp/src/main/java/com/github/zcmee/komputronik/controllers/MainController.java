package com.github.zcmee.komputronik.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({ "/login" })
    public String index() {
        return "login";
    }

    @GetMapping({ "/" })
    public String usersIndex() {
        return "redirect:/index";
    }

    @GetMapping({ "/index" })
    public String returnIndex(Model model, Authentication authentication) {
        model.addAttribute("name", authentication.getName());

        boolean isUser_OR = authentication.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->grantedAuthority.getAuthority().equalsIgnoreCase("USER_OR"));

        if (isUser_OR)
            return "indexor";
        else
            return "indexopl";
    }

}
