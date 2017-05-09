package com.medicalCabinet.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Andrada on 4/9/2017.
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    public ConflictException(Throwable cause) {
        super(cause);
    }

    public ConflictException() {
    }
}
