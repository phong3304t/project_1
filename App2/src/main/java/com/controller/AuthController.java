package com.controller;

import com.model.exceptions.UserNotFoundException;
import com.service.AuthService;

import java.sql.SQLException;

public class AuthController {

    private final AuthService authService;

    public AuthController() {
        authService = new AuthService();
    }

    public boolean loginAsUser(String userEmail, String password)
            throws UserNotFoundException, SQLException {
        if (userEmail == null || userEmail.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }
        return authService.loginAsUser(userEmail, password);
    }

    public boolean loginAsAdmin(String adminEmail, String password) {
        if (adminEmail == null || adminEmail.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }
        return authService.loginAsAdmin(adminEmail, password);
    }

    public boolean registerUser(String name, String email, String password) {
        if (email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        authService.registerUser(name, email, password);
        return true;
    }
}