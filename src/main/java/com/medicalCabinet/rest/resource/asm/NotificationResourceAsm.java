package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.NotificationResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 5/8/2017.
 */
public class NotificationResourceAsm extends ResourceAssemblerSupport<Notification,NotificationResource> {

    public NotificationResourceAsm() {
        super(DoctorPatientController.class, NotificationResource.class);
    }


    @Override
    public NotificationResource toResource(Notification entity) {
        NotificationResource res = new NotificationResource();
        res.setMessage(entity.getMessage());
        res.setRid(entity.getId());
        res.setUsername(entity.getUsername());
        Link self =linkTo(methodOn(DoctorPatientController.class).getNotification((entity.getId()))).withSelfRel();
        res.add(self);
        return res;

    }
}
