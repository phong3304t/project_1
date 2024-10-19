package com.library.model.transactions;

import java.time.LocalDate;

public class BorrowTransaction extends Transaction {
    private LocalDate dueDate = getTransactionDate().plusMonths(1);

    public BorrowTransaction(int documentId, int userId, String type,
                             LocalDate transactionDate, LocalDate dueDate) {
        super(documentId, userId, type, transactionDate);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String transactionInfo() {
        return "BorrowTransaction [" + super.transactionInfo() + ", Due Date: " + dueDate + "]";
    }
}
