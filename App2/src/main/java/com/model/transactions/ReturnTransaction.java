package com.model.transactions;

import java.time.LocalDate;

public class ReturnTransaction extends Transaction {
    private LocalDate returnDate;
    private double fine;

    /**
     * constructor 1.
     */
    public ReturnTransaction() {
        super();
        this.returnDate = LocalDate.parse("");
        this.fine = 0.0;
    }

    /**
     * constructor 2.
     *
     * @param ISBN doc ISBN
     * @param docType doc type
     * @param batch photo batch
     * @param userId user ID
     * @param borrowDate borrow date
     * @param returnDate return date
     * @param fine fine
     */
    public ReturnTransaction(String ISBN, String docType, int batch, int userId,
                             LocalDate borrowDate, LocalDate returnDate, double fine) {
        super(ISBN, docType, batch, userId, borrowDate);
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String getType() {
        return "Return";
    }
}