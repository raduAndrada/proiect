package com.medicalCabinet.core.repositories.validators;

import com.medicalCabinet.core.exceptions.InvalidCNPExeption;

/**
 * Created by Andrada on 5/6/2017.
 */
public interface CnpValidator {

    public boolean isValidCNP(String CNP) throws InvalidCNPExeption;
}
