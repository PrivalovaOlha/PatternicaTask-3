package com.example.task3.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean isPasswordCorrect(String password, String hashFromDatabase) {
        return passwordEncoder.matches(password, hashFromDatabase);
    }
}

