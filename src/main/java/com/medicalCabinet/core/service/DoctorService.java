package com.medicalCabinet.core.service;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.service.util.DoctorList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;

/**
 * Created by Andrada on 4/24/2017.
 */
public interface DoctorService {
    public MedicalHistory addConsultation(MedicalHistory newConsultation, Long patientId);
    public MedicalHistoryList getMedicalHistory(Long id);

    public DoctorList getAllDoctors();
    public Doctor getDoctorById(Long id);
}
