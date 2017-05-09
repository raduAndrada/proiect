package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.User;
import com.medicalCabinet.rest.mvc.PatientController;
import com.medicalCabinet.rest.mvc.UserController;
import com.medicalCabinet.rest.resource.PatientResource;
import com.medicalCabinet.rest.resource.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/24/2017.
 */
public class UserResourceAsm extends ResourceAssemblerSupport<User,UserResource>{

    public UserResourceAsm() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        UserResource res = new UserResource();
        res.setRid(entity.getId());
        res.setAddress(entity.getAddress());
        res.setAdmin(entity.isAdmin());
        res.setSecretary(entity.isSecretary());
        res.setDoctor(entity.isDoctor());
        res.setEmail(entity.getEmail());
        res.setName(entity.getName());
        res.setUsername(entity.getUsername());
        res.setPassword(entity.getPassword());

        Link self =linkTo(methodOn(UserController.class).getUser((entity.getId()))).withSelfRel();

        res.add(self);
        return res;
    }
}
