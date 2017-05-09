package com.medicalCabinet.rest.resource;

import com.medicalCabinet.core.models.Patient;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;

/**
 * Created by Andrada on 4/24/2017.
 */
public class PatientResource extends ResourceSupport{

    private String name;
    private String CNP;
    private Long rid;
    private Date DOB;
    private String address;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Patient toPatient()
    {
        Patient pat = new Patient();
        pat.setName(name);
        pat.setAddress(address);
        pat.setDOB(DOB);
        pat.setCNP(CNP);
        pat.setEmail(email);
        return pat;
    }
}
