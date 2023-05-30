package com.example.asm7_phamvanthanh;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "sale";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        System.out.println(encodedPassword);
    }

}

