package com.library.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {

    @FXML
    private Button addDocumentButton;

    @FXML
    private Button viewDocumentsButton;

    @FXML
    private void handleAddDocumentAction() {
        System.out.println("Navigating to Add Document View");
        // Logic điều hướng sang giao diện thêm tài liệu
    }

    @FXML
    private void handleViewDocumentsAction() {
        System.out.println("Navigating to View Documents");
        // Logic để hiển thị danh sách tài liệu
    }
}

