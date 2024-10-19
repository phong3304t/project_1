package com.library.service;

import com.library.model.users.*;
import com.library.model.exceptions.InsufficientQuantityException;
import com.library.repository.*;

public class ReturnService {
    public void returnDocument(int docId, String user_email, boolean isLate, double fine)
            throws InsufficientQuantityException {
        User user = (User) UserRepository.findUserByEmail(user_email);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.getBorrowedDocuments() < 1) {
            throw new InsufficientQuantityException("Insufficient borrowedDocument for user: "
                    + user.getBorrowedDocuments());
        }

        TransactionRepository.returnDocument(docId, user.getId(), isLate, fine);
        System.out.println("Document returned successfully.");
    }
}

