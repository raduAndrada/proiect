package com.medicalCabinet.core.service.util;

import com.medicalCabinet.core.models.MedicalHistory;

import java.util.ArrayList;

/**
 * Created by Andrada on 4/24/2017.
 */
public class MedicalHistoryList {

    private ArrayList<MedicalHistory> medicalHistories;

    public ArrayList<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(ArrayList<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }
}
