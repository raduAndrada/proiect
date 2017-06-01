package com.medicalCabinet.core.repositories;

import com.medicalCabinet.core.exceptions.InvalidAppointmentException;
import com.medicalCabinet.core.exceptions.InvalidCNPExeption;
import com.medicalCabinet.core.models.Appointment;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.core.service.util.NotificationList;
import com.medicalCabinet.core.service.util.PatientList;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Andrada on 4/24/2017.
 */

public interface SecretaryServiceRepo {


    public Patient createPatient(Patient newPatient) throws InvalidCNPExeption;
    public MedicalHistory addMedicalHistory(MedicalHistory med, Long patientId);
    public MedicalHistory getMedicalHistoryById(Long id);
    public MedicalHistoryList getMedicalHistoryForPatient(Long patientId);
    public MedicalHistoryList getMedicalHistoryForPatientByCNP(String CNP);
    public MedicalHistoryList getAllMedicalHistories();
    public Patient getPatientById(Long id);
    public Patient updatePatient(Patient patient);
    public Patient deletePatient(Long patientId);
    public Patient getPatientByCNP(String cnp);

    public PatientList getAllPatients();

    public Appointment createAppointment(Appointment newAppointment) throws InvalidAppointmentException;
    public Appointment deleteAppointment(Long appointmentId);
    public Appointment getAppointmentById(Long id);
    public Appointment updateAppointment(Long appointmentId, Appointment newAppointment) throws  InvalidAppointmentException;
    public AppointmentList getAllAppointmentsForPatient(Long id);
    public AppointmentList getAllAppointments();
    public AppointmentList getAllAppointmentsForDoctor(Long doctorId);

    public Notification getNotificationById(Long id);
    public NotificationList getAllNotifications();
    public Notification deleteNotificationById(Long id);
    public Notification createNotification(Notification note);

}
