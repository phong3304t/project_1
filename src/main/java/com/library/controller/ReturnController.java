package com.library.controller;

import com.library.model.exceptions.InsufficientQuantityException;
import com.library.service.ReturnService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ReturnController {
    private ReturnService returnService = new ReturnService();

    @FXML
    private TextField userEmailField;

    @FXML
    private TextField documentIdField;

    @FXML
    private TextField isLateField;

    @FXML
    private TextField fineField;

    @FXML
    private Text returnStatus;

    @FXML
    public void returnDocument() throws InsufficientQuantityException {
        String userEmail = userEmailField.getText();
        int documentId = Integer.parseInt(documentIdField.getText());
        boolean isLate = Boolean.parseBoolean(isLateField.getText());
        double fine = Double.parseDouble(fineField.getText());

        returnService.returnDocument(documentId, userEmail, isLate, fine);
        returnStatus.setText("Return successful!");
    }
}

