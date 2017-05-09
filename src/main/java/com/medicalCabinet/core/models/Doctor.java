package com.medicalCabinet.core.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andrada on 4/24/2017.
 */
@Entity
@Table(name ="doctor")
public class Doctor {
    private long id;
    private long notifications;
    private String speciality;
    private User user;
    private Set<Appointment> appointmentSet;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="notifications")
    public long getNotifications() {
        return notifications;
    }

    public void setNotifications(long notifications) {
        this.notifications = notifications;
    }

    @Column(name="speciality")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @ManyToOne
    @JoinColumn(name = "doctorId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    public Set<Appointment> getAppointmentSet() {
        return appointmentSet;
    }

    public void setAppointmentSet(Set<Appointment> appointmentSet) {
        this.appointmentSet = appointmentSet;
    }
}
