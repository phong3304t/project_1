package com.model.transactions;

import java.time.LocalDate;

public class BorrowTransaction extends Transaction {
    private LocalDate dueDate;

    /**
     * constructor 1.
     */
    public BorrowTransaction() {
        super();
        this.dueDate = LocalDate.parse("");
    }

    /**
     * constructor 2.
     *
     * @param ISBN doc ISBN
     * @param docType doc type
     * @param batch photo batch
     * @param userId user ID
     * @param borrowDate borrow date
     * @param dueDate due date
     */
    public BorrowTransaction(String ISBN, String docType, int batch, int userId,
                             LocalDate borrowDate, LocalDate dueDate) {
        super(ISBN, docType, batch, userId, borrowDate);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String getType() {
        return "Borrow";
    }
}
