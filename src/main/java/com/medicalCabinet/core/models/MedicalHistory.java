package com.medicalCabinet.core.models;

import javax.persistence.*;

/**
 * Created by Andrada on 4/24/2017.
 */

@Entity
@Table(name ="medicalhistory")
public class MedicalHistory {
    private long id;
    private String recommendations;
    private String testResults;
    private String diagnostic;
    private String previousDoctor;

    private Patient patient;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="recommendations")
    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @Column(name="testResults")
    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    @Column(name="diagnostic")
    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    @Column(name="previousDoctor")
    public String getPreviousDoctor() {
        return previousDoctor;
    }

    public void setPreviousDoctor(String previousDoctor) {
        this.previousDoctor = previousDoctor;
    }

    @ManyToOne
    @JoinColumn(name = "patientId")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
