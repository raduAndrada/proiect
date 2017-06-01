package com.medicalCabinet.core.security;


import com.medicalCabinet.core.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Andrada on 4/17/2017.
 */
public class AccountUserDetails implements UserDetails{
    private final User user;

    public AccountUserDetails(User user) {
        this.user = user ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = () -> {return "USER";};
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (user.isAdmin()) authority = () -> {return "ADMIN";};
        if (user.isSecretary()) authority = () -> {return "SECRETARY";};
        if (user.isDoctor()) authority = () -> {return "DOCTOR";};
        if (user.isPatient()) authority = () -> {return "PATIENT";};
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
