package com.medicalCabinet.rest.resource;

import com.medicalCabinet.core.models.Doctor;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/24/2017.
 */
public class DoctorListResource extends ResourceSupport {


    private List<DoctorResource> doctors= new ArrayList<>();

    public List<DoctorResource> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorResource> doctors) {
        this.doctors = doctors;
    }
}
