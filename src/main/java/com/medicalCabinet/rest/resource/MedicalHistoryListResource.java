package com.medicalCabinet.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/24/2017.
 */
public class MedicalHistoryListResource extends ResourceSupport {
private List<MedicalHistoryResource> medicalHistories  = new ArrayList<>();

    public List<MedicalHistoryResource> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<MedicalHistoryResource> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }
}
