package com.library.model.documents;

public class Magazine extends Document {
    private int issueNumber;

    public Magazine(String type, String title, String author, int quantity, int issueNumber) {
        super(type, title, author, quantity);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String getInfo() {
        return "Magazine [" + super.getInfo() + ", Issue Number: " + issueNumber + "]";
    }
}
