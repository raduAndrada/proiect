package com.medicalCabinet.rest.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.Patient;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Andrada on 4/24/2017.
 */
public class AppointmentResource extends ResourceSupport {
    private long rid;
    private long doctorId;
    private long patientId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp date;

    private String purpose;
    private String patient;
    private String doctor;


    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Appointment toAppointment()
    {
        Appointment app = new Appointment();
        app.setDate(date);
        app.setPurpose(purpose);
        app.setDoctorId(doctorId);
        app.setPatientId(patientId);
        return app;

    }
}
