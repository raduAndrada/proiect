package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.AppointmentListResource;
import com.medicalCabinet.rest.resource.AppointmentResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class AppointmentListResourceAsm extends ResourceAssemblerSupport<AppointmentList,AppointmentListResource> {

    public AppointmentListResourceAsm() {
        super(DoctorPatientController.class, AppointmentListResource.class);
    }


    @Override
    public AppointmentListResource toResource(AppointmentList entity) {
        List<AppointmentResource> resource = new AppointmentResourceAsm().toResources(entity.getAppointments());
        AppointmentListResource listResource= new AppointmentListResource();
        listResource.setAppointments(resource);
        listResource.add(linkTo(methodOn(DoctorPatientController.class).getAllAppointments()).withSelfRel());
        return listResource;
    }
}
