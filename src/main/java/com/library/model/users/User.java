package com.library.model.users;

public class User extends Guest {
    private int borrowedDocuments;

    public User(String position, String name, String email, String password, int borrowedDocuments) {
        super(position, name, email, password);
        this.borrowedDocuments = borrowedDocuments;
    }

    public int getBorrowedDocuments() {
        return borrowedDocuments;
    }

    public void setBorrowedDocuments(int borrowedDocuments) {
        this.borrowedDocuments = borrowedDocuments;
    }

    @Override
    public String userInfo() {
        return "User [" + super.userInfo() + ", BorrowedDocuments: " + borrowedDocuments + "]";
    }
}
