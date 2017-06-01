package com.medicalCabinet.core.service.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.repositories.AdminServiceRepo;
import com.medicalCabinet.core.service.AdminService;
import com.medicalCabinet.core.service.util.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Andrada on 4/24/2017.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminServiceRepo adminServiceRepo;

    public AdminServiceRepo getAdminServiceRepo() {
        return adminServiceRepo;
    }

    public void setAdminServiceRepo(AdminServiceRepo adminServiceRepo) {
        this.adminServiceRepo = adminServiceRepo;
    }

    @Override
    @Transactional
    public User createUser(User newUser) {
        return adminServiceRepo.createUser(newUser);
    }

    @Override
    @Transactional
    public User updateUser(Long userId, User newUser) {
        return adminServiceRepo.updateUser(userId, newUser);
    }

    @Override
    @Transactional
    public User deleteUser(Long id) {
        return adminServiceRepo.deleteUser(id);
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        return adminServiceRepo.findUserById(id);
    }

    @Override
    @Transactional
    public UserList getAllUsers() {
        return adminServiceRepo.getAllUsers();
    }

    @Override
    @Transactional
    public Doctor createDoctor(Long userId, Doctor doctor) {
        return adminServiceRepo.createDoctor(userId,doctor);
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        return adminServiceRepo.findUserByUsername(username);
    }

    @Override
    public User updateUserByUsername(String username, User newUser) {
        return adminServiceRepo.updateUserByUsername(username,newUser);
    }

    @Override
    public Patient findPatientByUsername(String username) {
        return adminServiceRepo.findPatientByUsername(username);
    }
}
