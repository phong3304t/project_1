package com.library.controller;

import javafx.scene.control.*;
import com.library.model.exceptions.UserNotFoundException;
import com.library.model.users.*;
import com.library.service.UserService;
import javafx.fxml.FXML;

import java.util.List;

public class UserController {

    private UserService userService = new UserService();

    @FXML
    private TextField positionField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField borrowedDocumentsField;

    @FXML
    private ListView<String> userList;

    @FXML
    public void addUser() {
        String position = positionField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        int borrowedDocuments = Integer.parseInt(borrowedDocumentsField.getText());
        User user = new User(position, name, email, password, borrowedDocuments);
        userService.addUser(user);
        clearFields();
    }

    @FXML
    public void addAdmin() {
        String position = positionField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        Admin admin = new Admin(position, name, email, password);
        userService.addAdmin(admin);
        clearFields();
    }

    @FXML
    public void findUserByEmail() throws UserNotFoundException {
        String email = emailField.getText();
        userService.findUserByEmail(email);
        clearFields();
    }

    @FXML
    public void showAllUsers() {
        userList.getItems().clear();
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            userList.getItems().add(user.toString());
        }
    }

    private void clearFields() {
        positionField.clear();
        nameField.clear();
        emailField.clear();
        passwordField.clear();
        borrowedDocumentsField.clear();
    }
}

