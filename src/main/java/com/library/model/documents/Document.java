package com.library.model.documents;

public abstract class Document {
    private static int idCounter = 1;
    private int id;
    private String type;
    private String title;
    private String author;
    private int quantity;

    public Document(String type, String title, String author, int quantity) {
        this.id = idCounter++;
        this.type = type;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("Book") || type.equals("Magazine") || type.equals("Thesis")) {
            this.type = type;
        } else {
            System.out.println("<Invalid document type>");
        }
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

    public String getInfo() {
        return "Type: " + type + ", Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
    }
}
