package com.example.asm7_phamvanthanh.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("")
    public String index() {
        return "home.html";
    }

    @GetMapping("/encode/{pass}")
    @ResponseBody
    public String encode(@PathVariable String pass)
    {
        return new BCryptPasswordEncoder().encode(pass);
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }
}
