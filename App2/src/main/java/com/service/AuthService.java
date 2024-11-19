package com.service;

import com.model.users.*;

public class AuthService {
    private final UserService userService;

    public AuthService() {
        userService = new UserService();
    }

    private static final Admin admin = new Admin(
            "Admin", "admin", "123");

    /**
     * User login.
     *
     * @param email user email
     * @param password user password
     * @return boolean
     */
    public boolean loginAsUser(String email, String password) {
        User user = userService.findUserByEmail(email);
        return user.getPassword().equals(password);
    }

    /**
     * Admin login.
     *
     * @param email admin
     * @param password 1234
     * @return boolean
     */
    public boolean loginAsAdmin(String email, String password) {
        return admin.getEmail().equals(email)
                & admin.getPassword().equals(password);
    }

    /**
     * Register.
     *
     * @param name user name
     * @param email email
     * @param password password
     */
    public void registerUser(String name, String email, String password) {
        User user = new User(name, email, password, 0);
        User existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser == null) {
            userService.addUser(user);
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Email already exists.");
        }
    }
}

