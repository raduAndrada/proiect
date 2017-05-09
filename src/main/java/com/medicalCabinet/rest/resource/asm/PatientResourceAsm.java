package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.mvc.PatientController;
import com.medicalCabinet.rest.resource.MedicalHistoryResource;
import com.medicalCabinet.rest.resource.PatientResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class PatientResourceAsm  extends ResourceAssemblerSupport<Patient,PatientResource>{

    public PatientResourceAsm() {
        super(PatientController.class, PatientResource.class);
    }

    @Override
    public PatientResource toResource(Patient entity) {
        PatientResource res = new PatientResource();
        res.setRid(entity.getId());
        res.setAddress(entity.getAddress());
        res.setCNP(entity.getCNP());
        res.setDOB(entity.getDOB());
        res.setEmail(entity.getEmail());
        res.setName(entity.getName());
        Link self =linkTo(methodOn(PatientController.class).getPatient((entity.getId()))).withSelfRel();

        res.add(self);
        return res;
    }
}
