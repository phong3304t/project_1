package com.library.service;

import com.library.model.exceptions.UserNotFoundException;
import com.library.model.users.*;
import com.library.repository.UserRepository;


import java.util.List;

public class UserService {
    // Thêm người dùng
    public void addUser(User user) {
        UserRepository.addUser(user);
        System.out.println("User added successfully.");
    }

    // Thêm Admin
    public void addAdmin(Admin admin) {
        UserRepository.addAdmin(admin);
        System.out.println("Admin added successfully.");
    }

    // Tìm người dùng theo email
    public User findUserByEmail(String email) throws UserNotFoundException {
        User user = (User) UserRepository.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
        return user;
    }

    // Lấy danh sách người dùng
    public List<User> getAllUsers() {
        return UserRepository.getUser();
    }
}

