package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.model.transactions.BorrowTransaction;
import com.model.transactions.ReturnTransaction;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.service.TransactionService;

import java.io.IOException;
import java.time.LocalDate;

public class TransactionController {

    @FXML
    private TextField ISBNField;
    @FXML
    private TextField docTypeField;
    @FXML
    private TextField batchField;
    @FXML
    private TextField userIdField;
    @FXML
    private DatePicker borrowDatePicker;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private DatePicker returnDatePicker;
    @FXML
    private TextField fineField;
    @FXML
    private Label transactionStatusLabel;

    private final TransactionService transactionService;

    public TransactionController() {
        transactionService = new TransactionService();
    }

    @FXML
    public void handleBorrowDocumentAction() {
        try {
            String ISBN = ISBNField.getText();
            String docType = docTypeField.getText();
            int batch = Integer.parseInt(batchField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            LocalDate borrowDate = borrowDatePicker.getValue();
            LocalDate dueDate = dueDatePicker.getValue();

            if (borrowDate == null || dueDate == null) {
                transactionStatusLabel.setText("Please select borrow and due dates.");
                return;
            }

            BorrowTransaction transaction = new BorrowTransaction(ISBN, docType,
                    batch, userId, borrowDate, dueDate);
            transactionService.borrowDocument(transaction);
            transactionStatusLabel.setText("Borrow successful!");
        } catch (NumberFormatException e) {
            transactionStatusLabel.setText("Invalid document or user ID.");
        } catch (Exception e) {
            transactionStatusLabel.setText("An error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void handleReturnDocumentAction() {
        try {
            String ISBN = ISBNField.getText();
            String docType = docTypeField.getText();
            int batch = Integer.parseInt(batchField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            LocalDate borrowDate = borrowDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            double fine = fineField.getText().isEmpty() ? 0.0 : Double.parseDouble(
                    fineField.getText());

            if (borrowDate == null || returnDate == null) {
                transactionStatusLabel.setText("Please select borrow and return dates.");
                return;
            }

            ReturnTransaction transaction = new ReturnTransaction(ISBN, docType,
                    batch, userId, borrowDate, returnDate, fine);
            transactionService.returnDocument(transaction);
            transactionStatusLabel.setText("Return successful!");
        } catch (NumberFormatException e) {
            transactionStatusLabel.setText("Invalid document, user ID, or fine amount.");
        } catch (Exception e) {
            transactionStatusLabel.setText("An error occurred: " + e.getMessage());
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

    @FXML
    public void handleBackUserAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/UserDashboardView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
