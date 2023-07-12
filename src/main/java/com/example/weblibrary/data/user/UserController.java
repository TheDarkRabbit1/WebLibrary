package com.example.weblibrary.data.user;

import com.example.weblibrary.data.user.userRoles.UserRoles;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/registration")
    public String userRegistrationForm(Model model){
        model.addAttribute("roles", UserRoles.values());
        model.addAttribute("user", new User());
        return "user/registrationForm";
    }
    @PostMapping("/registerNewUser")
    public String addNewUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("roles", UserRoles.values());
            model.addAttribute("user", user);
            return "user/registrationForm";
        }
        userService.insertUser(user);
        return "redirect:/books";
    }
}
