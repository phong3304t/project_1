package com.service;

import com.model.exceptions.InsufficientQuantityException;
import com.model.transactions.*;
import com.repository.*;

public class TransactionService {
    /**
     * borrow document.
     *
     * @param transaction transaction
     * @throws InsufficientQuantityException Quantity related
     */
    public void borrowDocument(BorrowTransaction transaction)
            throws InsufficientQuantityException {
        TransactionRepository.borrowDocument(transaction);
    }

    /**
     * return document.
     *
     * @param transaction transaction
     */
    public void returnDocument(ReturnTransaction transaction) {
        TransactionRepository.returnDocument(transaction);
    }
}
