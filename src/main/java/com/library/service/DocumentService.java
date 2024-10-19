package com.library.service;

import com.library.model.exceptions.DocumentNotFoundException;
import com.library.model.documents.*;
import com.library.repository.DocumentRepository;

import java.util.List;

public class DocumentService {
    public void addDocument(Document document) {
        DocumentRepository.addDocument(document);
        System.out.println("Document added successfully.");
    }

    public void removeDocument(int docId)  {
        DocumentRepository.removeDocument(docId);
        System.out.println("Document with ID '" + docId + "' deleted successfully.");
    }

    public void updateDocument(int docId, String type, String title, String author, int quantity,
                               String genre, int issueNumber, String topic) {
        DocumentRepository.updateDocument(docId, type, title, author, quantity, genre, issueNumber, topic);
        System.out.println("Document updated successfully.");
    }

    public Document findDocument(String keyword) throws DocumentNotFoundException {
        Document document = (Document) DocumentRepository.findDocument(keyword);
        if (document == null) {
            throw new DocumentNotFoundException("Document with title or author '" + keyword + "' not found.");
        }
        return document;
    }

    public List<Document> getAllDocuments() {
        return DocumentRepository.getDocuments();
    }


}

