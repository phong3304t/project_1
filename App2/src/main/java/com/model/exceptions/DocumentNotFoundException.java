package com.model.exceptions;

public class DocumentNotFoundException extends Exception {
    /**
     * Cannot find document in database.
     * @param message error
     */
    public DocumentNotFoundException(String message) {
        super(message);
    }
}