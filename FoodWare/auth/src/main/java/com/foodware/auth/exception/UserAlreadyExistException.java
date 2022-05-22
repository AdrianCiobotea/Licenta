package com.foodware.auth.exception;

public class UserAlreadyExistException extends Exception {
    /**
     * Exception thrown by system in case someone tries to register with already existing phone number
     * id in the system.
     */

    public UserAlreadyExistException() {
        super();
    }


    public UserAlreadyExistException(String message) {
        super(message);
    }


    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
