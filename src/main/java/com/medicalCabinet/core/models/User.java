package com.medicalCabinet.core.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrada on 4/24/2017.
 */
@Entity
@Table(name ="user")
public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private boolean admin;
    private boolean secretary;
    private boolean doctor;

    private Set<Doctor> doctors = new HashSet<Doctor>();


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="admin")
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Column(name="secretary")
    public boolean isSecretary() {
        return secretary;
    }

    public void setSecretary(boolean secretary) {
        this.secretary = secretary;
    }

    @Column(name="doctor")
    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
