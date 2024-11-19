package com.model.transactions;

import java.time.LocalDate;

public abstract class Transaction {
    private String ISBN;
    private String docType;
    private int batch;
    private int userId;
    private LocalDate borrowDate;

    /**
     * constructor 1.
     */
    public Transaction() {
        this.ISBN = "";
        this.docType = "";
        this.batch = 0;
        this.userId = 0;
        this.borrowDate = LocalDate.parse("");
    }

    /**
     * constructor 2.
     *
     * @param ISBN doc ISBN
     * @param docType doc type
     * @param batch photo batch
     * @param userId user ID
     * @param borrowDate borrow date
     */
    public Transaction(String ISBN, String docType, int batch,
                       int userId, LocalDate borrowDate) {
        this.ISBN = ISBN;
        this.docType = docType;
        this.batch = batch;
        this.userId = userId;
        this.borrowDate = borrowDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public abstract String getType();
}
