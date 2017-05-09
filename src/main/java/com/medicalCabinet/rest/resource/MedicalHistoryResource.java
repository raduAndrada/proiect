package com.medicalCabinet.rest.resource;

import com.medicalCabinet.core.models.MedicalHistory;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Andrada on 4/24/2017.
 */
public class MedicalHistoryResource extends ResourceSupport {

    private String recommendations;
    private String testResults;
    private String diagnostic;
    private String previousDoctor;
    private Long patient;
    private  Long rid;

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getPreviousDoctor() {
        return previousDoctor;
    }

    public void setPreviousDoctor(String previousDoctor) {
        this.previousDoctor = previousDoctor;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    public MedicalHistory toMedicalHistory(){
        MedicalHistory med = new MedicalHistory();
        med.setDiagnostic(diagnostic);
        med.setRecommendations(recommendations);
        med.setPreviousDoctor(previousDoctor);
        med.setTestResults(testResults);
        return med;
    }
}
