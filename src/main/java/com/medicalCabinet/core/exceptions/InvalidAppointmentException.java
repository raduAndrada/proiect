package com.medicalCabinet.core.exceptions;

/**
 * Created by Andrada on 5/6/2017.
 */
public class InvalidAppointmentException extends Exception {

    public InvalidAppointmentException() {
        super("Invalid parameters for appointment");
    }

    public InvalidAppointmentException(String message) {
        super(message);
    }

    public InvalidAppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
