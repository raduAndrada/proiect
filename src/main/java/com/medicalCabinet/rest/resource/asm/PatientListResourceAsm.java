package com.medicalCabinet.rest.resource.asm;


import com.medicalCabinet.core.service.util.PatientList;
import com.medicalCabinet.rest.mvc.PatientController;
import com.medicalCabinet.rest.resource.PatientListResource;
import com.medicalCabinet.rest.resource.PatientResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class PatientListResourceAsm extends ResourceAssemblerSupport<PatientList,PatientListResource> {

    public PatientListResourceAsm() {
        super(PatientController.class, PatientListResource.class);
    }

    @Override
    public PatientListResource toResource(PatientList entity) {
        List<PatientResource> resource = new PatientResourceAsm().toResources(entity.getPatients());
        PatientListResource listResource= new PatientListResource();
        listResource.setPatients(resource);
        listResource.add(linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel());
        return listResource;
    }
}
