package com.medicalCabinet.core.service.impl;

import com.medicalCabinet.core.exceptions.InvalidAppointmentException;
import com.medicalCabinet.core.exceptions.InvalidCNPExeption;
import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.repositories.SecretaryServiceRepo;
import com.medicalCabinet.core.service.SecretaryService;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.core.service.util.NotificationList;
import com.medicalCabinet.core.service.util.PatientList;
import com.medicalCabinet.rest.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Andrada on 4/24/2017.
 */

@Service
@Transactional
public class SecretaryServiceImpl implements SecretaryService {
    @Autowired
    SecretaryServiceRepo secretaryServiceRepo;

    public SecretaryServiceRepo getSecretaryServiceRepo() {
        return secretaryServiceRepo;
    }

    public void setSecretaryServiceRepo(SecretaryServiceRepo secretaryServiceRepo) {
        this.secretaryServiceRepo = secretaryServiceRepo;
    }

    @Override
    public Patient createPatient(Patient newPatient) {
        try {
            return secretaryServiceRepo.createPatient(newPatient);
        } catch (InvalidCNPExeption invalidCNPExeption) {
            invalidCNPExeption.printStackTrace();
            return null;
        }
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return  secretaryServiceRepo.updatePatient(patient);
    }

    @Override
    @Transactional
    public MedicalHistory addMedicalHistory(MedicalHistory med, Long patientId) {
        return secretaryServiceRepo.addMedicalHistory(med,patientId);
    }

    @Override
    public MedicalHistory getMedicalHistoryById(Long id) {
        return secretaryServiceRepo.getMedicalHistoryById(id);
    }

    @Override
    @Transactional
    public MedicalHistoryList getMedicalHistoryForPatient(Long patientId) {
        return secretaryServiceRepo.getMedicalHistoryForPatient(patientId);
    }

    @Override
    public MedicalHistoryList getMedicalHistoryForPatientByCNP(String CNP) {
        return secretaryServiceRepo.getMedicalHistoryForPatientByCNP(CNP);
    }

    @Override
    public MedicalHistoryList getAllMedicalHistories() {
        return secretaryServiceRepo.getAllMedicalHistories();
    }

    @Override
    @Transactional
    public Patient getPatientById(Long id) {
        return secretaryServiceRepo.getPatientById(id);
    }

    @Override
    public Patient deletePatient(Long patientId) {
        return secretaryServiceRepo.deletePatient(patientId);
    }

    @Override
    public Patient getPatientByCNP(String cnp) {
        return secretaryServiceRepo.getPatientByCNP(cnp);
    }

    @Override
    public PatientList getAllPatients() {
        return secretaryServiceRepo.getAllPatients();
    }

    @Override
    public Appointment createAppointment(Appointment newAppointment) throws BadRequestException {
        try {
            return secretaryServiceRepo.createAppointment(newAppointment);
        } catch (InvalidAppointmentException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @Override
    public Appointment deleteAppointment(Long appointmentId) {
        return secretaryServiceRepo.deleteAppointment(appointmentId);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return secretaryServiceRepo.getAppointmentById(id);
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment newAppointment) throws  BadRequestException{
        try {
            return secretaryServiceRepo.updateAppointment(appointmentId,newAppointment);
        } catch (InvalidAppointmentException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }


    @Override
    public AppointmentList getAllAppointmentsForPatient(Long id) {
        return secretaryServiceRepo.getAllAppointmentsForPatient(id);
    }

    @Override
    public AppointmentList getAllAppointments() {
        return secretaryServiceRepo.getAllAppointments();
    }

    @Override
    public AppointmentList getAllAppointmentsForDoctor(Long doctorId) {
        return secretaryServiceRepo.getAllAppointmentsForDoctor(doctorId);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return secretaryServiceRepo.getNotificationById(id);
    }

    @Override
    public NotificationList getAllNotifications() {
        return secretaryServiceRepo.getAllNotifications();
    }

    @Override
    public Notification deleteNotificationById(Long id) {
        return secretaryServiceRepo.deleteNotificationById(id);
    }

    @Override
    public Notification createNotification(Notification note) {
        return secretaryServiceRepo.createNotification(note);
    }
}
