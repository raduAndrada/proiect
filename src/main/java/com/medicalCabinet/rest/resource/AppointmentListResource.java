package com.medicalCabinet.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/24/2017.
 */
public class AppointmentListResource extends ResourceSupport {

    private List<AppointmentResource> appointments= new ArrayList<>();

    public List<AppointmentResource> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentResource> appointments) {
        this.appointments = appointments;
    }
}
