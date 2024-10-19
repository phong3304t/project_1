package com.library.model.transactions;

import java.time.LocalDate;

public abstract class Transaction {
    private int documentId;
    private int userId;
    private String type;
    private LocalDate transactionDate;

    public Transaction(int documentId, int userId, String type, LocalDate transactionDate) {
        this.documentId = documentId;
        this.userId = userId;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("Borrow") || type.equals("Return")) {
            this.type = type;
        } else {
            System.out.println("<Invalid transaction type>");
        }
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String transactionInfo() {
        return "Document ID: " + documentId + ", User ID: " + userId +
                ", Type: " + type + ", Date: " + transactionDate;
    }
}
