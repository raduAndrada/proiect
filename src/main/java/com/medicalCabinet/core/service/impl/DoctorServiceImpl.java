package com.medicalCabinet.core.service.impl;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.repositories.DoctorServiceRepo;
import com.medicalCabinet.core.repositories.SecretaryServiceRepo;
import com.medicalCabinet.core.service.DoctorService;
import com.medicalCabinet.core.service.util.DoctorList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Andrada on 4/24/2017.
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorServiceRepo doctorServiceRepo;

    public DoctorServiceRepo getDoctorServiceRepo() {
        return doctorServiceRepo;
    }

    public void setDoctorServiceRepo(DoctorServiceRepo doctorServiceRepo) {
        this.doctorServiceRepo = doctorServiceRepo;
    }

    @Override
    public MedicalHistory addConsultation(MedicalHistory newConsultation, Long patientId) {
        return doctorServiceRepo.addConsultation(newConsultation, patientId);
    }

    @Override
    public MedicalHistoryList getMedicalHistory(Long id) {
        return doctorServiceRepo.getMedicalHistory(id);
    }

    @Override
    public DoctorList getAllDoctors() {
        return doctorServiceRepo.getAllDoctors();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorServiceRepo.getDoctorById(id);
    }
}
