package com.medicalCabinet.core.repositories.jpa;

import com.medicalCabinet.core.exceptions.InvalidAppointmentException;
import com.medicalCabinet.core.exceptions.InvalidCNPExeption;
import com.medicalCabinet.core.models.*;
import com.medicalCabinet.core.repositories.SecretaryServiceRepo;
import com.medicalCabinet.core.repositories.validators.AppointmentValidator;
import com.medicalCabinet.core.repositories.validators.CnpValidator;
import com.medicalCabinet.core.service.util.AppointmentList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.core.service.util.NotificationList;
import com.medicalCabinet.core.service.util.PatientList;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrada on 4/25/2017.
 */
@Repository
public class SecretaryRepoJpa implements SecretaryServiceRepo {
    @Autowired(required=true)
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Patient createPatient(Patient newPatient) throws InvalidCNPExeption{
        String cnp = newPatient.getCNP();
        CnpValidator validator = new CnpValidator() {
            @Override
            public boolean isValidCNP(String CNP) throws InvalidCNPExeption {
                if (cnp.length() != 13) throw new InvalidCNPExeption();

                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dob = formatter.format(newPatient.getDOB());
                String mm = dob.substring(5,7);
                String dd= dob.substring(8,10);


                String s = cnp.substring(0, 1);
                if (s.equals("3") || s.equals("4")) throw new InvalidCNPExeption();

                String ll = cnp.substring(3, 5);

                if (!ll.equals(mm)) throw new InvalidCNPExeption();

                String zz = cnp.substring(5, 7);
                if (! zz.equals(dd)) throw new InvalidCNPExeption();


                String jj = cnp.substring(7, 9);
                int aux = Integer.parseInt(jj);
                if (aux > 52) throw new InvalidCNPExeption();

                return false;
                }
        };
        validator.isValidCNP("");
        sessionFactory.getCurrentSession().saveOrUpdate(newPatient);
        User usr = new User();
        usr.setPatient(true);
        usr.setName(newPatient.getName());
        usr.setEmail(newPatient.getEmail());
        usr.setUsername(newPatient.getCNP());
        usr.setPassword(newPatient.getName().replaceAll(" ",""));
        usr.setAddress(newPatient.getAddress());
        usr.setAdmin(false);
        usr.setDoctor(false);
        usr.setSecretary(false);
        sessionFactory.getCurrentSession().saveOrUpdate(usr);
        return newPatient;
    }

    @Override
    public MedicalHistory addMedicalHistory(MedicalHistory med, Long patientId) {
       Patient pat = (Patient) sessionFactory.getCurrentSession().get(Patient.class,new Long(patientId));

       if (pat != null)
       {
           med.setPatient(pat);
           sessionFactory.getCurrentSession().saveOrUpdate(med);
       }
       return med;
    }

    @Override
    public MedicalHistory getMedicalHistoryById(Long id) {
        return (MedicalHistory) sessionFactory.getCurrentSession().get(MedicalHistory.class,new Long(id));
    }

    @Override
    public MedicalHistoryList getMedicalHistoryForPatient(Long patientId) {
       Patient pat = getPatientById(patientId);
       ArrayList<MedicalHistory> list = new ArrayList<>();
       for(MedicalHistory med : pat.getHistorySet())
       {
           list.add(med);
       }
       MedicalHistoryList medicalHistoryList = new MedicalHistoryList();
       medicalHistoryList.setMedicalHistories(list);
       return medicalHistoryList;
    }

    @Override
    public MedicalHistoryList getMedicalHistoryForPatientByCNP(String CNP) {
        Patient pat = getPatientByCNP(CNP);
        MedicalHistoryList list = new MedicalHistoryList();
        ArrayList<MedicalHistory> meds = new ArrayList<>();
        for (MedicalHistory m : pat.getHistorySet())
        {
            meds.add(m);
        }
        list.setMedicalHistories(meds);
        return list;
    }

    @Override
    public MedicalHistoryList getAllMedicalHistories() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM MedicalHistory");
        ArrayList<MedicalHistory> list = (ArrayList<MedicalHistory>) query.list();
        MedicalHistoryList medicalHistoryList = new MedicalHistoryList();
        medicalHistoryList.setMedicalHistories(list);
        return medicalHistoryList;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient pat = (Patient) sessionFactory.getCurrentSession().get(Patient.class,new Long(id));
        return pat;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        sessionFactory.getCurrentSession().saveOrUpdate(patient);
        return patient;
    }

    @Override
    public Patient deletePatient(Long patientId) {
        Patient pat = getPatientById(patientId);
        MedicalHistoryList list = getMedicalHistoryForPatient(patientId);
        for(MedicalHistory m: list.getMedicalHistories()) sessionFactory.getCurrentSession().delete(m);
        sessionFactory.getCurrentSession().delete(pat);
        return pat;
    }

    @Override
    public Patient getPatientByCNP(String cnp) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Patient WHERE cnp = :pat_cnp");
        query.setParameter("pat_cnp",cnp);
        List <Patient> results = (ArrayList<Patient>) query.list();
        return results.get(0);
    }


    @Override
    public PatientList getAllPatients() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Patient");
        ArrayList<Patient> patients = (ArrayList<Patient>)query.list();
        PatientList patientList = new PatientList();
        patientList.setPatients(patients);
        return patientList;
    }

    @Override
    public Appointment createAppointment(Appointment newAppointment) throws InvalidAppointmentException{
        Patient pat = getPatientById(newAppointment.getPatientId());
        Doctor doc = (Doctor) sessionFactory.getCurrentSession().get(Doctor.class,new Long(newAppointment.getDoctorId()));
        AppointmentList appsForDoc = getAllAppointmentsForDoctor(doc.getId());
        AppointmentList appsForPat = getAllAppointmentsForPatient(pat.getId());

        AppointmentValidator validator = new AppointmentValidator() {
            @Override
            public void isValidAppointment() throws InvalidAppointmentException {
                for (Appointment a: appsForDoc.getAppointments()
                     ) {
                    if(a.getDate().equals(newAppointment.getDate())) throw new InvalidAppointmentException();
                }

                for (Appointment a: appsForPat.getAppointments()
                        ) {
                    if(a.getDate().equals(newAppointment.getDate())) throw new InvalidAppointmentException();
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(newAppointment.getDate());

                DateFormat dayFormate=new SimpleDateFormat("EEEE");
                String dayFromDate=dayFormate.format(newAppointment.getDate());

                if (dayFromDate.contains("Sun")||dayFromDate.contains("Sat")) throw new InvalidAppointmentException();

                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = localDateFormat.format(newAppointment.getDate());

                if (! (time.substring(3,5).equals("00")||time.substring(3,5).equals("30"))) throw new InvalidAppointmentException();
                String closingTime = "17:30:00";
                String openingTime="10:00:00";
                try {
                    Date temp = localDateFormat.parse(closingTime);
                    Date crt = localDateFormat.parse(time);
                    if (temp.before(crt)) throw new InvalidAppointmentException();

                    temp = localDateFormat.parse(openingTime);
                    if(crt.before(temp)) throw new InvalidAppointmentException();

                    crt=formatter.parse(s);
                    String today = formatter.format(new Date());
                    temp = formatter.parse(today);
                    if (crt.before(temp)) throw new InvalidAppointmentException();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        };

        if(doc !=null && pat !=null) {
            newAppointment.setPatient(pat);
            newAppointment.setDoctor(doc);
            sessionFactory.getCurrentSession().saveOrUpdate(newAppointment);
            validator.isValidAppointment();
        }
        return newAppointment;
    }

    @Override
    public Appointment deleteAppointment(Long appointmentId) {
        Appointment app = getAppointmentById(appointmentId);
        sessionFactory.getCurrentSession().delete(app);
        return app;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return (Appointment) sessionFactory.getCurrentSession().get(Appointment.class,new Long(id));
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment appointment) throws InvalidAppointmentException {

        Appointment app = getAppointmentById(appointmentId);
        AppointmentList appsForDoc = getAllAppointmentsForDoctor(app.getDoctor().getId());
        AppointmentList appsForPat = getAllAppointmentsForPatient(app.getPatient().getId());

        AppointmentValidator validator = new AppointmentValidator() {
            @Override
            public void isValidAppointment() throws InvalidAppointmentException {
                for (Appointment a: appsForDoc.getAppointments()
                        ) {
                    if(a.getDate().equals(appointment.getDate())) throw new InvalidAppointmentException();
                }

                for (Appointment a: appsForPat.getAppointments()
                        ) {
                    if(a.getDate().equals(appointment.getDate())) throw new InvalidAppointmentException();
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(appointment.getDate());

                DateFormat dayFormate=new SimpleDateFormat("EEEE");
                String dayFromDate=dayFormate.format(appointment.getDate());

                if (dayFromDate.contains("Sun")||dayFromDate.contains("Sat")) throw new InvalidAppointmentException();

                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = localDateFormat.format(appointment.getDate());

                if (! (time.substring(3,5).equals("00")||time.substring(3,5).equals("30"))) throw new InvalidAppointmentException();

                String closingTime = "17:30:00";
                String openingTime="10:00:00";
                try {
                    Date temp = localDateFormat.parse(closingTime);
                    Date crt = localDateFormat.parse(time);
                    if (temp.before(crt)) throw new InvalidAppointmentException();

                    temp = localDateFormat.parse(openingTime);
                    if(crt.before(temp)) throw new InvalidAppointmentException();

                    crt=formatter.parse(s);
                    String today = formatter.format(new Date());
                    temp = formatter.parse(today);
                    if (crt.before(temp)) throw new InvalidAppointmentException();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        };
        validator.isValidAppointment();
        app.setDate(appointment.getDate());
        sessionFactory.getCurrentSession().saveOrUpdate(app);
        return app;
    }

    @Override
    public AppointmentList getAllAppointmentsForPatient(Long id) {
        Patient pat = getPatientById(id);
        AppointmentList list = new AppointmentList();
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment ap: pat.getAppointments()) {
                    appointments.add(ap);
        }
        list.setAppointments(appointments);
        return list;
    }

    @Override
    public AppointmentList getAllAppointments() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Appointment");
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) query.list();
        AppointmentList list  = new AppointmentList();
        list.setAppointments(appointments);
        return list;
    }

    @Override
    public AppointmentList getAllAppointmentsForDoctor(Long doctorId) {
        Doctor doc = (Doctor) sessionFactory.getCurrentSession().get(Doctor.class,new Long(doctorId));
        AppointmentList list = new AppointmentList();
        ArrayList<Appointment> appointments = new ArrayList<>();
        for(Appointment ap : doc.getAppointmentSet())
        {
            appointments.add(ap);
            System.out.println(ap.getDate());
        }
        list.setAppointments(appointments);
        return list;
    }

    @Override
    public Notification getNotificationById(Long id) {
        Notification note = (Notification) sessionFactory.getCurrentSession().get(Notification.class,new Long(id));
        return note;
    }

    @Override
    public NotificationList getAllNotifications() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Notification");
        ArrayList<Notification> notifications = (ArrayList<Notification>) query.list();
        NotificationList list = new NotificationList();
        list.setNotifications(notifications);
        return list;
    }

    @Override
    public Notification deleteNotificationById(Long id) {
        Notification note = getNotificationById(id);
        sessionFactory.getCurrentSession().delete(note);
        return note;
    }

    @Override
    public Notification createNotification(Notification note) {
        sessionFactory.getCurrentSession().saveOrUpdate(note);
        return note;
    }
}
