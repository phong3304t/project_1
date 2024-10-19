package com.library.model.transactions;

import java.time.LocalDate;

public class ReturnTransaction extends Transaction {
    private boolean isLate;
    private double fine;

    public ReturnTransaction(int documentId, int userId, String type,
                             LocalDate transactionDate, boolean isLate, double fine) {
        super(documentId, userId, type, transactionDate);
        this.isLate = isLate;
        this.fine = fine;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean isLate) {
        this.isLate = isLate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String transactionInfo() {
        return "ReturnTransaction [" + super.transactionInfo() + ", Late: " + isLate + ", Fine: " + fine + "]";
    }
}
