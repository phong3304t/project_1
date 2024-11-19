package com.service;

import com.model.users.*;
import com.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    /**
     * add user.
     *
     * @param user user
     */
    public void addUser(User user) {
        UserRepository.addUser(user);
    }

    /**
     * find user by email.
     *
     * @param email email
     * @return user
     */
    public User findUserByEmail(String email) {
        return UserRepository.findUserByEmail(email);
    }

    /**
     * view all user.
     *
     * @return list
     * @throws SQLException SQL related
     */
    public List<User> viewAllUsers() throws SQLException {
        return UserRepository.viewAllUser();
    }
}

