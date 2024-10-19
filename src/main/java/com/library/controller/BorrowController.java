package com.library.controller;

import com.library.model.exceptions.InsufficientQuantityException;
import com.library.service.BorrowService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BorrowController {
    private BorrowService borrowService = new BorrowService();

    @FXML
    private TextField userIdField;

    @FXML
    private TextField keywordField;

    @FXML
    private Text borrowStatus;

    @FXML
    public void borrowDocument() {
        int userId = Integer.parseInt(userIdField.getText());
        String keyword = keywordField.getText();

        try {
            borrowService.borrowDocument(keyword, userId);
            borrowStatus.setText("Borrow successful!");
        } catch (InsufficientQuantityException e) {
            borrowStatus.setText("Error: " + e.getMessage());
        }
    }
}

