package com.library.service;

import com.library.model.documents.*;
import com.library.model.exceptions.InsufficientQuantityException;
import com.library.repository.*;

public class BorrowService {
    public void borrowDocument(String keyword, int userId) throws InsufficientQuantityException {
        Document document = (Document) DocumentRepository.findDocument(keyword);

        if (document == null) {
            System.out.println("Document not found.");
            return;
        }

        if (document.getQuantity() < 1) {
            throw new InsufficientQuantityException("Insufficient quantity for document: " + document.getTitle());
        }

        TransactionRepository.borrowDocument(document.getId(), userId);
        System.out.println("Document borrowed successfully.");
    }
}

