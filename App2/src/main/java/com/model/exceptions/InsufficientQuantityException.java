package com.model.exceptions;

public class InsufficientQuantityException extends Exception {
    /**
     * Do not have enough quantity.
     * @param message error
     */
    public InsufficientQuantityException(String message) {
        super(message);
    }
}