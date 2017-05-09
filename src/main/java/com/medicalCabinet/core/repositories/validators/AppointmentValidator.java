package com.medicalCabinet.core.repositories.validators;

import com.medicalCabinet.core.exceptions.InvalidAppointmentException;
import org.omg.CORBA.DynAnyPackage.Invalid;

/**
 * Created by Andrada on 5/6/2017.
 */
public interface AppointmentValidator {

    public void isValidAppointment() throws InvalidAppointmentException ;
}
