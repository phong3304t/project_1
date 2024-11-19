package com.model.exceptions;

public class UserNotFoundException extends Exception {
    /**
     * Cannot find user in database.
     * @param message error
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
