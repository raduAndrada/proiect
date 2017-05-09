package com.medicalCabinet.rest.resource.asm;

import com.medicalCabinet.core.models.Notification;
import com.medicalCabinet.core.service.util.NotificationList;
import com.medicalCabinet.rest.mvc.DoctorPatientController;
import com.medicalCabinet.rest.resource.MedicalHistoryListResource;
import com.medicalCabinet.rest.resource.MedicalHistoryResource;
import com.medicalCabinet.rest.resource.NotificationListResource;
import com.medicalCabinet.rest.resource.NotificationResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 5/8/2017.
 */
public class NotificationListResourceAsm extends ResourceAssemblerSupport<NotificationList,NotificationListResource> {

    public NotificationListResourceAsm() {
        super(DoctorPatientController.class, NotificationListResource.class);
    }



    @Override
    public NotificationListResource toResource(NotificationList entity) {
        List<NotificationResource> resource = new NotificationResourceAsm().toResources(entity.getNotifications());
        NotificationListResource listResource= new NotificationListResource();
        listResource.setNotifications(resource);
        listResource.add(linkTo(methodOn(DoctorPatientController.class).getAllNotifications()).withSelfRel());
        return listResource;
    }
}
