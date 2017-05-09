package com.medicalCabinet.core.service.util;

import com.medicalCabinet.core.models.User;

import java.util.ArrayList;

/**
 * Created by Andrada on 4/24/2017.
 */
public class UserList {
        private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public UserList(ArrayList<User> users) {
        this.users = users;
    }

    public UserList() {
    }
}
