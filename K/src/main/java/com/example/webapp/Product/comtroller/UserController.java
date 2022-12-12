package com.example.webapp.Product.comtroller;

import com.example.webapp.Mod.User;
import com.example.webapp.servis.UserServis;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServis userServis ;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")//принимает юзера
    public String createUser(User user, Model model) {
        if(!userServis.createUser(user)){
            model.addAttribute("errorMessage","Пользоватиль с таким email уже существует");
            return "registration";
        }
        return "login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}
