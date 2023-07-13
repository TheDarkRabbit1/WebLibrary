package com.example.weblibrary.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String displayHomePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("authorities", authentication.getAuthorities());
        }
        return "/homePage";
    }
}
