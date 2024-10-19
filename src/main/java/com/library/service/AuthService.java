package com.library.service;

import com.library.model.exceptions.UserNotFoundException;
import com.library.model.users.*;
import com.library.repository.UserRepository;

public class AuthService {
    public Guest login(String position, String email, String password) throws UserNotFoundException {
        if (position.equals("Admin")) {
            Admin admin = (Admin) UserRepository.findUserByEmail(email);

            if (admin == null) {
                throw new UserNotFoundException("Admin with email " + email + " not found.");
            }

            if (admin != null && admin.getPassword().equals(password)) {
                System.out.println("Admin logged in successfully.");
                return admin;
            }
        } else {
                User user = (User) UserRepository.findUserByEmail(email);

                if (user == null) {
                    throw new UserNotFoundException("User with email " + email + " not found.");
                }

                if (user != null && user.getPassword().equals(password)) {
                    System.out.println("User logged in successfully.");
                    return user;
                }
        }

        System.out.println("Invalid email or password.");
        return null;
    }

    // Đăng ký
    public void register(User user) {
        User existingUser = (User) UserRepository.findUserByEmail(user.getEmail());

        if (existingUser == null) {
            UserRepository.addUser(user);
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Email already exists.");
        }
    }
}

