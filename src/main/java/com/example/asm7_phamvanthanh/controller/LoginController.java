package com.example.asm7_phamvanthanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }
}
