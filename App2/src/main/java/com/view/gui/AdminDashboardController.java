package com.view.gui;

import com.controller.DocumentController;
import com.controller.TransactionController;
import com.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button addDocumentButton;
    @FXML
    private Button removeDocumentButton;
    @FXML
    private Button updateDocumentButton;
    @FXML
    private Button findDocumentButton;
    @FXML
    private Button viewAllDocumentButton;
    @FXML
    private Button addUserButton;
    @FXML
    private Button borrowDocumentButton;
    @FXML
    private Button returnDocumentButton;
    @FXML
    private Button viewAllUserButton;
    @FXML
    private Button backButton;

    @FXML
    private void handleAddDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AddDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Add Document View");
    }

    @FXML
    private void handleRemoveDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/RemoveDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Remove Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Remove Document View");
    }

    @FXML
    private void handleFindDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/FindDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Find Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Find Document View");
    }

    @FXML
    private void handleUpdateDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/UpdateDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Update Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Update Document View");
    }

    @FXML
    private void handleViewAllDocumentsAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/GetAllDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("List All Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to View Documents");
    }

    @FXML
    private void handleAddUserAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AddUserView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Add User View");
    }

    @FXML
    private void handleBorrowDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/BorrowDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Borrow Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Borrow Document View");
    }

    @FXML
    private void handleReturnDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/ReturnDocumentView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Return Document");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to Return Document View");
    }

    @FXML
    private void handleViewAllUsersAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/GetAllUserView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("List All User");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Navigating to View Users");
    }

    public void handleBackAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.setTitle("Library Management System");
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

