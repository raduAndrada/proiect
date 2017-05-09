package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.AppointmentResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.sql.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class AppointmentResourceAsm extends ResourceAssemblerSupport<Appointment,AppointmentResource> {


    public AppointmentResourceAsm() {
        super(DoctorPatientController.class, AppointmentResource.class);
    }

    @Override
    public AppointmentResource toResource(Appointment entity) {
        AppointmentResource res = new AppointmentResource();

        res.setDate(entity.getDate());
        res.setRid(entity.getId());
        res.setDoctor(entity.getDoctor().getUser().getUsername());
        res.setPatient(entity.getPatient().getName());
        res.setPurpose(entity.getPurpose());


        Link self =linkTo(methodOn(DoctorPatientController.class).getAppointment((entity.getId()))).withSelfRel();

        res.add(self);
        return res;

    }
}
