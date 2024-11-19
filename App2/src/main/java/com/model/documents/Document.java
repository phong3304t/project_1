package com.model.documents;

public abstract class Document {
    protected String ISBN;
    protected String title;
    protected String author;
    protected int quantity;

    /**
     * constructor 1.
     */
    public Document() {
        this.ISBN = "";
        this.title = "";
        this.author = "";
        this.quantity = 0;
    }

    /**
     * constructor 2.
     *
     * @param ISBN document ISBN
     * @param title document title
     * @param author document author
     * @param quantity document quantity
     */
    public Document(String ISBN, String title, String author, int quantity) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract String getType();
}
