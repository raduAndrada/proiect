package com.medicalCabinet.core.exceptions;

/**
 * Created by Andrada on 5/6/2017.
 */
public class InvalidCNPExeption extends Exception{

    public InvalidCNPExeption() {
        super("This CNP is invalid");
    }

    public InvalidCNPExeption(String message) {
        super(message);
    }

    public InvalidCNPExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
