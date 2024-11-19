package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.model.users.User;
import com.service.UserService;

import java.io.IOException;
import java.util.List;

public class UserController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField borrowedDocumentsField;
    @FXML
    private ListView<String> userListView;
    @FXML
    private Label userStatusLabel;

    private final UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public void handleAddUserAction() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            int borrowedDocuments = 0;
            User user = new User(name, email, password, borrowedDocuments);
            userService.addUser(user);
            userStatusLabel.setText("User added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleViewAllUserAction() {
        try {
            List<User> users = userService.viewAllUsers();
            userListView.getItems().clear();

            for (User user : users) {
                String info = String.format("ID: %d | Name: %s "
                                + "| Email: %s| Borrowed Documents:%d",
                        user.getId(), user.getName(),
                        user.getEmail(), user.getBorrowedDocuments());
                userListView.getItems().add(info);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleBackAdminAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminDashboardView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
