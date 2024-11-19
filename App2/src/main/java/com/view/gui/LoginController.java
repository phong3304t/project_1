package com.view.gui;

import com.controller.AuthController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.model.exceptions.UserNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField userEmailField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Label userLoginStatusLabel;
    @FXML
    private TextField adminEmailField;
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private Label adminLoginStatusLabel;
    @FXML
    private Label registerStatusLabel;

    private final AuthController authController;

    public LoginController() {
        authController = new AuthController();
    }

    @FXML
    private void handleUserLoginAction() {
        String userEmail = userEmailField.getText();
        String password = userPasswordField.getText();

        try {
            if (authController.loginAsUser(userEmail, password)) {
                userLoginStatusLabel.setText("Login successful!");
                loadView("/fxml/UserDashboardView.fxml", "User Dashboard");
            } else {
                userLoginStatusLabel.setText("Invalid email or password.");
            }
        } catch (UserNotFoundException | SQLException e) {
            userLoginStatusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdminLoginAction() {
        String adminEmail = adminEmailField.getText();
        String password = adminPasswordField.getText();

        if (authController.loginAsAdmin(adminEmail, password)) {
            adminLoginStatusLabel.setText("Login successful!");
            loadView("/fxml/AdminDashboardView.fxml", "Admin Dashboard");
        } else {
            adminLoginStatusLabel.setText("Invalid email or password.");
        }
    }

    @FXML
    public void handleUserRegisterAction() {
        loadView("/fxml/UserRegister.fxml", "User Registration");
    }

    @FXML
    public void handleRegisterAction(ActionEvent event) {
        String name = nameField.getText();
        String email = userEmailField.getText();
        String password = userPasswordField.getText();

        try {
            if (authController.registerUser(name, email, password)) {
                registerStatusLabel.setText("User registered successfully!");
                handleBackAction(event);
            } else {
                registerStatusLabel.setText("Registration failed.");
            }
        } catch (Exception e) {
            registerStatusLabel.setText("Error: " + e.getMessage());
        }
    }

    private void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage primaryStage = (Stage) userEmailField.getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error loading view: " + e.getMessage());
        }
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/LoginView.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 1000, 600));
            newStage.setTitle("Library Management System");
            newStage.show();

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            System.out.println("Error loading new stage: " + e.getMessage());
        }
    }
}
