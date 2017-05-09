package com.medicalCabinet.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/24/2017.
 */
public class PatientListResource extends ResourceSupport {

    List<PatientResource> patients = new ArrayList<>();

    public List<PatientResource> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientResource> patients) {
        this.patients = patients;
    }
}
