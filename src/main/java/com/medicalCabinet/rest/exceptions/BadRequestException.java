package com.medicalCabinet.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Andrada on 4/9/2017.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    super("Specified date is invalid");
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
