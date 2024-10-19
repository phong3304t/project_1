package com.library.model.documents;

public class Book extends Document {
    private String genre;

    public Book(String type, String title, String author, int quantity, String genre) {
        super(type, title, author, quantity);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getInfo() {
        return "Book [" + super.getInfo() + ", Genre: " + genre + "]";
    }
}
