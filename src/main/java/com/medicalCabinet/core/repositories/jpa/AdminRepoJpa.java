package com.medicalCabinet.core.repositories.jpa;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.repositories.AdminServiceRepo;
import com.medicalCabinet.core.service.util.UserList;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        if (query.list().size()> 0 )return (User) query.list().get(0);
        else return null;
    }
}
