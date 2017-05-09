package com.medicalCabinet.core.security;


import com.medicalCabinet.core.models.User;
import com.medicalCabinet.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Andrada on 4/17/2017.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private AdminService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(user.toString());
        }
        return new AccountUserDetails(user);
    }
}
