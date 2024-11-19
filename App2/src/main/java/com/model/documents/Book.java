package com.model.documents;

public class Book extends Document {
    private String publisher;
    private String categories;
    private String description;
    private int pageNumber;

    /**
     * constructor 1.
     */
    public Book() {
        super();
        this.publisher = "";
        this.categories = "";
        this.description = "";
        this.pageNumber = 0;
    }

    /**
     * constructor 2.
     *
     * @param ISBN document ISBN
     * @param title document title
     * @param author document author
     * @param quantity document quantity
     * @param publisher book publisher
     * @param categories book categories
     * @param description book description
     * @param pageNumber book pageNumber
     */
    public Book(String ISBN, String title, String author, int quantity,
                String publisher, String categories, String description, int pageNumber) {
        super(ISBN, title, author, quantity);
        this.publisher = publisher;
        this.categories = categories;
        this.description = description;
        this.pageNumber = pageNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String getType() {
        return "Book";
    }
}
