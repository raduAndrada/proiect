package com.medicalCabinet.core.service.exceptions;

/**
 * Created by Andrada on 4/9/2017.
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }

    public UserDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

