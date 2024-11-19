package com.repository;

import com.model.users.*;
import com.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final Connection connection = DatabaseConnection.getConnection();

    /**
     * Add user.
     *
     * @param user user
     */
    public static void addUser(User user) {
        String sql = "INSERT INTO "
                + "Users(name, email, password, borrowedDocuments) "
                + "VALUES(?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getBorrowedDocuments());
            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Find user by email.
     *
     * @param user_email email
     * @return users
     */
    public static User findUserByEmail(String user_email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user_email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int borrowedDocuments = rs.getInt("borrowedDocuments");
                return new User(name, email, password, borrowedDocuments);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * View all user.
     *
     * @return List
     */
    public static List<User> viewAllUser() throws SQLException {
        List<User> results = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int borrowedDocuments = rs.getInt("borrowedDocuments");
                User user = new User(name, email, password, borrowedDocuments);
                results.add(user);
            }
        }
        return results;
    }
}