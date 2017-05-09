package com.medicalCabinet.core.service.util;

import com.medicalCabinet.core.models.Patient;

import java.util.ArrayList;

/**
 * Created by Andrada on 4/24/2017.
 */
public class PatientList {

    private ArrayList<Patient> patients;

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}
