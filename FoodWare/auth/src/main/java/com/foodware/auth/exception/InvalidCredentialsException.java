package com.foodware.auth.exception;

public class InvalidCredentialsException extends Exception {
    /**
     * Exception thrown by system in case someone tries to login with invalid credentials
     */

    public InvalidCredentialsException() {
        super();
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}

