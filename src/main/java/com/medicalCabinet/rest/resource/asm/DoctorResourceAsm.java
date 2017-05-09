package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.rest.mvc.DoctorController;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.AppointmentListResource;
import com.medicalCabinet.rest.resource.AppointmentResource;
import com.medicalCabinet.rest.resource.DoctorResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class DoctorResourceAsm extends ResourceAssemblerSupport<Doctor,DoctorResource>  {
    @Override
    public DoctorResource toResource(Doctor entity) {
        DoctorResource res = new DoctorResource();
        res.setRid(entity.getId());
        res.setSpeciality(entity.getSpeciality());
        res.setNotifications(entity.getNotifications());
        res.setName(entity.getUser().getName());

        Link self =linkTo(methodOn(DoctorController.class).getDoctor((entity.getId()))).withSelfRel();

        res.add(self);
        return res;
    }

    public DoctorResourceAsm() {
        super(DoctorController.class, DoctorResource.class);
    }
}
