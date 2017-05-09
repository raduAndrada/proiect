package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.rest.mvc.DoctorController;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.DoctorListResource;
import com.medicalCabinet.rest.resource.DoctorResource;
import com.medicalCabinet.rest.resource.MedicalHistoryListResource;
import com.medicalCabinet.rest.resource.MedicalHistoryResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class MedicalHistoryListResourceAsm extends ResourceAssemblerSupport<MedicalHistoryList,MedicalHistoryListResource> {

    public MedicalHistoryListResourceAsm() {
        super(DoctorPatientController.class,MedicalHistoryListResource.class );
    }

    @Override
    public MedicalHistoryListResource toResource(MedicalHistoryList entity) {
        List<MedicalHistoryResource> resource = new MedicalHistoryResourceAsm().toResources(entity.getMedicalHistories());
        MedicalHistoryListResource listResource= new MedicalHistoryListResource();
        listResource.setMedicalHistories(resource);
        listResource.add(linkTo(methodOn(DoctorPatientController.class).getAllHistory()).withSelfRel());
        return listResource;
    }
}
