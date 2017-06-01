package com.medicalCabinet.core.repositories.jpa;

import com.medicalCabinet.core.models.*;
import com.medicalCabinet.core.repositories.AdminServiceRepo;
import com.medicalCabinet.core.service.util.UserList;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrada on 4/25/2017.
 */
@Repository
public class AdminRepoJpa implements AdminServiceRepo{

    @Autowired(required=true)
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User createUser(User newUser) {
        sessionFactory.getCurrentSession().saveOrUpdate(newUser);
        return newUser;
    }

    @Override
    public User updateUser(Long userId, User newUser) {
        User usr = findUserById(userId);
        usr.setAddress(newUser.getAddress());
        usr.setName(newUser.getName());
        usr.setPassword(newUser.getPassword());
        sessionFactory.getCurrentSession().update(usr);
        return usr;
    }

    @Override
    public User deleteUser(Long id) {
        User usr = findUserById(id);
        sessionFactory.getCurrentSession().delete(usr);
        return usr;
    }

    @Override
    public User findUserById(Long id) {

        return (User) sessionFactory.getCurrentSession().get(User.class,new Long(id))  ;
    }

    @Override
    public UserList getAllUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User");
        ArrayList<User> users = (ArrayList<User>) query.list();
        UserList userList = new UserList();
        userList.setUsers(users);
        return userList;
    }

    @Override
    public Doctor createDoctor(Long userId, Doctor doctor) {
        User newUser = findUserById(userId);
        doctor.setUser(newUser);
        sessionFactory.getCurrentSession().saveOrUpdate(doctor);

        return doctor;
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = :username");
        query.setParameter("username", username);
        if (query.list().size()> 0 )
        {
            User usr = (User) query.list().get(0);
            return usr;
        }
        else return null;
    }

    @Override
    public Patient findPatientByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Patient WHERE cnp = :username");
        query.setParameter("username", username);
        if (query.list().size()> 0 ){

            Patient pat= (Patient) query.list().get(0);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date =  new Date(Calendar.getInstance().getTime().getTime());
            System.out.println(dateFormat.format(date));
            if(pat.getAppointments()!=null) {
                for (Appointment a : pat.getAppointments()) {
                    if (dateFormat.format(a.getDate()).equals(dateFormat.format(date))) {
                        Notification note = new Notification();
                        note.setUsername(username);
                        dateFormat = new SimpleDateFormat("HH:mm:ss");
                        note.setMessage("Reminder! Appointment at " + dateFormat.format(a.getDate()));
                        sessionFactory.getCurrentSession().saveOrUpdate(note);
                    }
                }
            }
            return pat;
        }
        else return null;
    }

    @Override
    public User updateUserByUsername(String username, User newUser) {
        User us = findUserByUsername(username);
        return updateUser(us.getId(),newUser);
    }
}
