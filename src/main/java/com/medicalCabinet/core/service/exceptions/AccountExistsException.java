package com.medicalCabinet.core.service.exceptions;

/**
 * Created by Andrada on 4/9/2017.
 */
public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(Throwable cause) {
        super(cause);
    }
}
