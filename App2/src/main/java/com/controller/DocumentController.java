package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.model.documents.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.service.DocumentService;

import java.io.IOException;
import java.util.List;

public class DocumentController {

    @FXML
    private TextField ISBNField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField categoriesField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField pageNumberField;
    @FXML
    private TextField batchField;
    @FXML
    private Label documentStatusLabel;
    @FXML
    private ListView<String> documentListView;

    private final DocumentService documentService;

    public DocumentController() {
        documentService = new DocumentService();
    }

    @FXML
    public void handleAddDocumentAction() {
        try {
            String ISBN = ISBNField.getText();
            String type = typeField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String publisher = publisherField.getText();
            String categories = categoriesField.getText();
            String description = descriptionArea.getText();
            int pageNumber = Integer.parseInt(pageNumberField.getText());

            Document doc = null;
            if ("Book".equals(type)) {
                doc = new Book(ISBN, title, author, quantity,
                        publisher, categories, description, pageNumber);
            } else if ("Photo".equals(type)) {
                int batch = Integer.parseInt(batchField.getText());
                doc = new Photo(ISBN, title, author, quantity,
                        publisher, categories, description, pageNumber, batch);
            }
            documentService.addDocument(doc);
            documentStatusLabel.setText("Document added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleRemoveDocumentAction() {
        try {
            String ISBN = ISBNField.getText();
            String type = typeField.getText();
            int batch = Integer.parseInt(batchField.getText());
            documentService.removeDocument(ISBN, type, batch);
            documentStatusLabel.setText("Document removed successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleUpdateDocumentAction() {
        try {
            String ISBN = ISBNField.getText();
            String type = typeField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String publisher = publisherField.getText();
            String categories = categoriesField.getText();
            String description = descriptionArea.getText();
            int pageNumber = Integer.parseInt(pageNumberField.getText());
            Document doc = null;
            if ("Book".equals(type)) {
                doc = new Book(ISBN, title, author, quantity,
                        publisher, categories, description, pageNumber);
            } else if ("Photo".equals(type)) {
                int batch = Integer.parseInt(batchField.getText());
                doc = new Photo(ISBN, title, author, quantity,
                        publisher, categories, description, pageNumber, batch);
            }
            documentService.updateDocument(doc);
            documentStatusLabel.setText("Document updated successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleFindDocumentAction() {
        try {
            String keyword = titleField.getText();
            List<Document> documents = documentService.findDocument(keyword);
            documentListView.getItems().clear();

            for (Document doc : documents) {
                if (doc.getType().equals("Book")) {
                    String info = String.format("ISBN: %s | Type: %s | Title: %s "
                                    + "| Author: %s | Quantity: %d | Publisher: %s "
                                    + "| Categories: %s | Description: %s | Page Number: %d",
                            doc.getISBN(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Book) doc).getPublisher(),
                            ((Book) doc).getCategories(), ((Book) doc).getDescription(),
                            ((Book) doc).getPageNumber());
                    documentListView.getItems().add(info);
                } else if (doc.getType().equals("Photo")) {
                    String info = String.format("ISBN: %s | Type: %s | Title: %s "
                                    + "| Author: %s | Quantity: %d | Publisher: %s "
                                    + "| Categories: %s | Description: %s "
                                    + "| Page Number: %d | Batch: %d",
                            doc.getISBN(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Photo) doc).getPublisher(),
                            ((Photo) doc).getCategories(), ((Photo) doc).getDescription(),
                            ((Photo) doc).getPageNumber(), ((Photo) doc).getBatch());
                    documentListView.getItems().add(info);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleViewAllDocumentAction() {
        try {
            List<Document> documents = documentService.viewAllDocuments();
            documentListView.getItems().clear();

            for (Document doc : documents) {
                if (doc.getType().equals("Book")) {
                    String info = String.format("ISBN: %s | Type: %s | Title: %s "
                                    + "| Author: %s | Quantity: %d | Publisher: %s "
                                    + "| Categories: %s | Description: %s | Page Number: %d",
                            doc.getISBN(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Book) doc).getPublisher(),
                            ((Book) doc).getCategories(), ((Book) doc).getDescription(),
                            ((Book) doc).getPageNumber());
                    documentListView.getItems().add(info);
                } else if (doc.getType().equals("Photo")) {
                    String info = String.format("ISBN: %s | Type: %s | Title: %s "
                                    + "| Author: %s | Quantity: %d | Publisher: %s "
                                    + "| Categories: %s | Description: %s "
                                    + "| Page Number: %d | Batch: %d",
                            doc.getISBN(), doc.getType(), doc.getTitle(),
                            doc.getAuthor(), doc.getQuantity(), ((Photo) doc).getPublisher(),
                            ((Photo) doc).getCategories(), ((Photo) doc).getDescription(),
                            ((Photo) doc).getPageNumber(), ((Photo) doc).getBatch());
                    documentListView.getItems().add(info);
                }
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