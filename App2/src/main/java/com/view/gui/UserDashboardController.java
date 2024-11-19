package com.view.gui;

import com.controller.DocumentController;
import com.controller.TransactionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardController {

    @FXML
    private Button findDocumentButton;
    @FXML
    private Button viewAllDocumentButton;
    @FXML
    private Button borrowDocumentButton;
    @FXML
    private Button returnDocumentButton;
    @FXML
    private Button backButton;

    @FXML
    private void handleFindDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/UserFindDocumentView.fxml"));
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
    private void handleViewAllDocumentsAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/UserGetAllDocumentView.fxml"));
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
    private void handleBorrowDocumentAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/UserBorrowDocumentView.fxml"));
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
                    "/fxml/UserReturnDocumentView.fxml"));
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