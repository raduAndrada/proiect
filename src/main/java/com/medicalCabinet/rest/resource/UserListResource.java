package com.medicalCabinet.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/24/2017.
 */
public class UserListResource extends ResourceSupport {
    List<UserResource> users = new ArrayList<>();

    public List<UserResource> getUsers() {
        return users;
    }

    public void setUsers(List<UserResource> users) {
        this.users = users;
    }
}
