package com.model.documents;

public class Photo extends Book {
    private int batch;

    /**
     * constructor 1.
     */
    public Photo() {
        super();
        this.batch = 0;
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
     * @param batch photo batch
     */
    public Photo(String ISBN, String title, String author,
                 int quantity, String publisher, String categories,
                 String description, int pageNumber, int batch) {
        super(ISBN, title, author, quantity, publisher,
                categories, description, pageNumber);
        this.batch = batch;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String getType() {
        return "Photo";
    }
}
