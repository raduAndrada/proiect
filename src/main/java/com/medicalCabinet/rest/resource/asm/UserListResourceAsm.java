package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.service.util.UserList;
import com.medicalCabinet.rest.mvc.PatientController;
import com.medicalCabinet.rest.mvc.UserController;
import com.medicalCabinet.rest.resource.PatientListResource;
import com.medicalCabinet.rest.resource.PatientResource;
import com.medicalCabinet.rest.resource.UserListResource;
import com.medicalCabinet.rest.resource.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class UserListResourceAsm extends ResourceAssemblerSupport<UserList,UserListResource> {

    public UserListResourceAsm() {
        super(UserController.class, UserListResource.class);
    }

    @Override
    public UserListResource toResource(UserList entity) {
        List<UserResource> resource = new UserResourceAsm().toResources(entity.getUsers());
        UserListResource listResource= new UserListResource();
        listResource.setUsers(resource);
        listResource.add(linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
        return listResource;
    }
}
