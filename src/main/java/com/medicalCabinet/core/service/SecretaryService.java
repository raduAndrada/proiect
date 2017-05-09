package com.medicalCabinet.core.service;

import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.core.service.util.NotificationList;
import com.medicalCabinet.core.service.util.PatientList;
import com.medicalCabinet.rest.exceptions.BadRequestException;

/**
 * Created by Andrada on 4/24/2017.
 */
public interface SecretaryService {
    public Patient createPatient(Patient newPatient);
    public Patient updatePatient(Patient patient);
    public MedicalHistory addMedicalHistory(MedicalHistory med, Long patientId);
    public MedicalHistory getMedicalHistoryById(Long id);
    public MedicalHistoryList getMedicalHistoryForPatient(Long patientId);
    public MedicalHistoryList getAllMedicalHistories();
    public Patient getPatientById(Long id);
    public Patient deletePatient(Long patientId);
    public Patient getPatientByCNP(String cnp);

    public PatientList getAllPatients();

    public Appointment createAppointment(Appointment newAppointment) throws BadRequestException;
    public Appointment deleteAppointment(Long appointmentId);
    public Appointment getAppointmentById(Long id);
    public Appointment updateAppointment(Long appointmentId, Appointment newAppointment) throws BadRequestException;
    public AppointmentList getAllAppointmentsForPatient(Long id);
    public AppointmentList getAllAppointments();
    public AppointmentList getAllAppointmentsForDoctor(Long doctorId);

    public Notification getNotificationById(Long id);
    public NotificationList getAllNotifications();
    public Notification deleteNotificationById(Long id);
    public Notification createNotification(Notification note);

}
