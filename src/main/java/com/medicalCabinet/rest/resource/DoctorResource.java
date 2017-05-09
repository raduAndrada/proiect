package com.medicalCabinet.rest.resource;

import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.User;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Andrada on 4/24/2017.
 */
public class DoctorResource extends ResourceSupport{

    private Long rid;
    private long notifications;
    private String speciality;
    private String name;



    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public long getNotifications() {
        return notifications;
    }

    public void setNotifications(long notifications) {
        this.notifications = notifications;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Doctor toDoctor()
    {
        Doctor doc = new Doctor();
        doc.setSpeciality(speciality);
        doc.setNotifications(notifications);

        return doc;

    }
}
