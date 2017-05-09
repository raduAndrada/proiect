package com.medicalCabinet.core.repositories.jpa;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.MedicalHistory;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.repositories.DoctorServiceRepo;
import com.medicalCabinet.core.service.util.DoctorList;
import com.medicalCabinet.core.service.util.MedicalHistoryList;
import com.medicalCabinet.rest.resource.MedicalHistoryListResource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by Andrada on 4/25/2017.
 */

@Repository
public class DoctorRepoJpa implements DoctorServiceRepo{

    @Autowired(required=true)
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @Override
    public MedicalHistory addConsultation(MedicalHistory newConsultation, Long patientId) {
        Patient pat = (Patient) sessionFactory.getCurrentSession().get(Patient.class,new Long(patientId));
        if (pat != null)
        {
            newConsultation.setPatient(pat);
            sessionFactory.getCurrentSession().saveOrUpdate(newConsultation);
        }
        return newConsultation;
    }

    @Override
    public MedicalHistoryList getMedicalHistory(Long id) {
        Patient pat = (Patient) sessionFactory.getCurrentSession().get(Patient.class,new Long(id));
        ArrayList<MedicalHistory> medicalHistories = new ArrayList<>();
        for(MedicalHistory med: pat.getHistorySet())
        {
            medicalHistories.add(med);
        }
        MedicalHistoryList list = new MedicalHistoryList();
        list.setMedicalHistories(medicalHistories);
        return list;
    }

    @Override
    public DoctorList getAllDoctors() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Doctor");
        ArrayList<Doctor> doctors =(ArrayList<Doctor>) query.list();
        if (doctors != null)
        {
            DoctorList list = new DoctorList();
            list.setDoctors(doctors);
            return list;
        }
        return null;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Doctor doc = (Doctor) sessionFactory.getCurrentSession().get(Doctor.class,new Long(id));
        if(doc!=null) return doc;
        else return null;
    }
}
