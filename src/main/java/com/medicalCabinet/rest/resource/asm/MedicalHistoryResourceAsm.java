package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.rest.mvc.DoctorController;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.DoctorResource;
import com.medicalCabinet.rest.resource.MedicalHistoryResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class MedicalHistoryResourceAsm extends ResourceAssemblerSupport<MedicalHistory,MedicalHistoryResource> {

    public MedicalHistoryResourceAsm() {
        super(DoctorPatientController.class, MedicalHistoryResource.class);
    }

    @Override
    public MedicalHistoryResource toResource(MedicalHistory entity) {
        MedicalHistoryResource res = new MedicalHistoryResource();
        res.setRid(entity.getId());
        res.setDiagnostic(entity.getDiagnostic());
        res.setPreviousDoctor(entity.getPreviousDoctor());
        res.setRecommendations(entity.getRecommendations());
        res.setTestResults(entity.getTestResults());
        res.setFilename(entity.getFilename());
        res.setPatient(entity.getPatient().getId());
        Link self =linkTo(methodOn(DoctorPatientController.class).getMedicalHistory((entity.getId()))).withSelfRel();

        res.add(self);
        return res;
    }
}
