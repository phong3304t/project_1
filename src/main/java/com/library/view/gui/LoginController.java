package com.library.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginController {

    @FXML
    private TextField userUsernameField;

    @FXML
    private PasswordField userPasswordField;

    @FXML
    private TextField adminUsernameField;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    private void handleUserLoginAction(ActionEvent event) {
        String username = userUsernameField.getText();
        String password = userPasswordField.getText();

        if (username.equals("user") && password.equals("userpass")) {
            System.out.println("User login successful!");
            loadUserDashboard();  // Chuyển sang giao diện người dùng
        } else {
            System.out.println("User login failed!");
        }
    }

    @FXML
    private void handleAdminLoginAction(ActionEvent event) {
        String username = adminUsernameField.getText();
        String password = adminPasswordField.getText();

        if (username.equals("admin") && password.equals("adminpass")) {
            System.out.println("Admin login successful!");
            loadAdminDashboard();  // Chuyển sang giao diện admin
        } else {
            System.out.println("Admin login failed!");
        }
    }

    private void loadUserDashboard() {
        try {
            Stage stage = (Stage) userUsernameField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserDashboardView.fxml"));
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAdminDashboard() {
        try {
            Stage stage = (Stage) adminUsernameField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminDashboardView.fxml"));
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
