package com.library.repository;

import com.library.model.users.*;
import com.library.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    /**
     * #6.
     * @param user User
     */
    public static void addUser(User user) {
        String sql = "INSERT INTO Users(position, name, email, password, borrowedDocuments)" +
                " VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, user.getPosition());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getBorrowedDocuments());
            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addAdmin(Admin admin) {
        String sql = "INSERT INTO Admins(position, name, email, password) VALUES(?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, admin.getPosition());
            pstmt.setString(2, admin.getName());
            pstmt.setString(3, admin.getEmail());
            pstmt.setString(4, admin.getPassword());
            pstmt.executeUpdate();
            System.out.println("Admin added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<User> findUserByEmail(String user_email) {
        List<User> results = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, user_email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String position = rs.getString("position");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int borrowedDocuments = rs.getInt("borrowedDocuments");
                User user = new User(position, name, email, password, borrowedDocuments);
                results.add(user);
            } else {
                System.out.println("Email not found.");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    /**
     * #9.
     * @return List
     */
    public static List<User> getUser() {
        List<User> results = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                String position = rs.getString("position");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int borrowedDocuments = rs.getInt("borrowedDocuments");
                User user = new User(position, name, email, password, borrowedDocuments);
                results.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
