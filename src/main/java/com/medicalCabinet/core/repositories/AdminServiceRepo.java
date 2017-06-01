package com.medicalCabinet.core.repositories;

import com.medicalCabinet.core.models.Doctor;
import com.medicalCabinet.core.models.Patient;
import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.service.util.UserList;

/**
 * Created by Andrada on 4/24/2017.
 */
public interface AdminServiceRepo {

    public User createUser(User newUser);
    public User updateUser(Long userId, User newUser);
    public User deleteUser(Long id);
    public User findUserById(Long id);
    public UserList getAllUsers();
    public Doctor createDoctor(Long userId, Doctor doctor);
    public User findUserByUsername(String username);

    public Patient findPatientByUsername(String username);
    public User updateUserByUsername(String username, User newUser);
}
