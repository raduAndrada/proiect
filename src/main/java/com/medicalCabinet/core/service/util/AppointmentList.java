package com.medicalCabinet.core.service.util;

import com.medicalCabinet.core.models.Appointment;

import java.util.ArrayList;

/**
 * Created by Andrada on 4/24/2017.
 */
public class AppointmentList {

    private ArrayList<Appointment> appointments;

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
}
