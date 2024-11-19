package com.model.users;

public class User extends Admin {
    private int borrowedDocuments;

    /**
     * constructor 1.
     */
    public User() {
        super();
        this.borrowedDocuments = 0;
    }

    /**
     * User constructor.
     *
     * @param name name
     * @param email email
     * @param password password
     * @param borrowedDocuments documents
     */
    public User(String name, String email,
                String password, int borrowedDocuments) {
        super(name, email, password);
        this.borrowedDocuments = borrowedDocuments;
    }

    public int getBorrowedDocuments() {
        return borrowedDocuments;
    }

    public void setBorrowedDocuments(int borrowedDocuments) {
        this.borrowedDocuments = borrowedDocuments;
    }

    @Override
    public String getPosition() {
        return "User";
    }
}
