package com.library.controller;

import com.library.model.documents.*;
import com.library.model.exceptions.DocumentNotFoundException;
import com.library.service.DocumentService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DocumentController {

    private DocumentService documentService = new DocumentService();

    @FXML
    private TextField idField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField issueNumberField;

    @FXML
    private TextField topicField;

    @FXML
    private ListView<String> documentList;

    @FXML
    public void addDocument() {
        String type = typeField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        if (type.equals("Book")) {
            String genre = genreField.getText();

            Document book = new Book(type, title, author, quantity, genre);
            documentService.addDocument(book);
        } else if (type.equals("Magazine")) {
            int issueNumber = Integer.parseInt(issueNumberField.getText());

            Magazine magazine = new Magazine(type, title, author, quantity, issueNumber);
            documentService.addDocument(magazine);
        } else {
            String topic = topicField.getText();

            Thesis thesis = new Thesis(type, title, author, quantity, topic);
            documentService.addDocument(thesis);
        }
        clearFields();
    }

    @FXML
    public void removeDocument() {
        int id = Integer.parseInt(idField.getText());
        documentService.removeDocument(id);
        clearFields();
    }

    @FXML
    public void updateDocument() {
        int id = Integer.parseInt(idField.getText());
        String type = typeField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String genre = genreField.getText();
        int issueNumber = Integer.parseInt(issueNumberField.getText());
        String topic = topicField.getText();

        documentService.updateDocument(id, type, title, author, quantity, genre, issueNumber, topic);
        clearFields();
    }

    @FXML
    public void findDocumentByTitle() throws DocumentNotFoundException {
        String title = titleField.getText();
        documentService.findDocument(title);
        clearFields();
    }

    @FXML
    public void findDocumentByAuthor() throws DocumentNotFoundException {
        String author = authorField.getText();
        documentService.findDocument(author);
        clearFields();
    }

    @FXML
    public void showAllDocuments() {
        documentList.getItems().clear();
        for (Document doc : documentService.getAllDocuments()) {
            documentList.getItems().add(doc.getInfo());
        }
    }

    private void clearFields() {
        idField.clear();
        typeField.clear();
        titleField.clear();
        authorField.clear();
        quantityField.clear();
        genreField.clear();
        issueNumberField.clear();
        topicField.clear();
    }
}

