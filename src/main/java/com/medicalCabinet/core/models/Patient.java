package com.medicalCabinet.core.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Andrada on 4/24/2017.
 */
@Entity
@Table(name ="patient")
@Transactional
public class Patient {

    private String name;
    private String CNP;
    private long id;
    private Date DOB;
    private String address;
    private String email;
    private Set<MedicalHistory> historySet ;
    private Set<Appointment> appointments;

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="CNP")
    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="DOB")
    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    public Set<MedicalHistory> getHistorySet() {
        return historySet;
    }

    public void setHistorySet(Set<MedicalHistory> historySet) {
        this.historySet = historySet;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
