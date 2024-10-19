package com.library.controller;

import com.library.model.exceptions.UserNotFoundException;
import com.library.model.users.*;
import com.library.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AuthController {
    private AuthService authService = new AuthService();

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField borrowedDocumentsField;

    @FXML
    private Text loginStatus;

    @FXML
    public void handleUserLogin() throws UserNotFoundException {
        String position = "User";
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = (User) authService.login(position, email, password);
        if (user != null) {
            loginStatus.setText("Login successful!");
        } else {
            loginStatus.setText("Invalid email or password.");
        }
    }

    @FXML
    public void handleAdminLogin() throws UserNotFoundException {
        String position = "Admin";
        String email = emailField.getText();
        String password = passwordField.getText();

        Admin admin = (Admin) authService.login(position, email, password);
        if (admin != null) {
            loginStatus.setText("Login successful!");
        } else {
            loginStatus.setText("Invalid email or password.");
        }
    }

    @FXML
    public void handleRegister() {
        String position = "User";
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        int borrowedDocuments = Integer.parseInt(borrowedDocumentsField.getText());

        User newUser = new User(position, name, email, password, borrowedDocuments);
        authService.register(newUser);
        loginStatus.setText("Registration successful!");
    }
}

