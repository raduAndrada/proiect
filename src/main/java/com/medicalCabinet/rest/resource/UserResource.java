package com.medicalCabinet.rest.resource;

import com.medicalCabinet.core.models.User;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Andrada on 4/24/2017.
 */
public class UserResource extends ResourceSupport {

    private Long rid;
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private boolean admin;
    private boolean secretary;
    private boolean doctor;
    private String speciality;


    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isSecretary() {
        return secretary;
    }

    public void setSecretary(boolean secretary) {
        this.secretary = secretary;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public User toUser()
    {
       User user = new User();
       user.setAddress(address);
       user.setAdmin(admin);
       user.setDoctor(doctor);
       user.setEmail(email);
       user.setName(name);
       user.setPassword(password);
       user.setSecretary(secretary);
       user.setUsername(username);
       return user;
    }
}
