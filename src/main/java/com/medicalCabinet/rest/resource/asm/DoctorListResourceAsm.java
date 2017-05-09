package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.DoctorList;
import com.medicalCabinet.rest.mvc.DoctorController;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.AppointmentListResource;
import com.medicalCabinet.rest.resource.AppointmentResource;
import com.medicalCabinet.rest.resource.DoctorListResource;
import com.medicalCabinet.rest.resource.DoctorResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class DoctorListResourceAsm extends ResourceAssemblerSupport<DoctorList,DoctorListResource>{

    public DoctorListResourceAsm() {
        super(DoctorController.class, DoctorListResource.class);
    }

    @Override
    public DoctorListResource toResource(DoctorList entity) {
        List<DoctorResource> resource = new DoctorResourceAsm().toResources(entity.getDoctors());
        DoctorListResource listResource= new DoctorListResource();
        listResource.setDoctors(resource);
        listResource.add(linkTo(methodOn(DoctorController.class).getAllDoctors()).withSelfRel());
        return listResource;
    }
}
