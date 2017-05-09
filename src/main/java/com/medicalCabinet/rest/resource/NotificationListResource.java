package com.medicalCabinet.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 5/8/2017.
 */
public class NotificationListResource extends ResourceSupport {

    private List<NotificationResource> notifications = new ArrayList<>();

    public List<NotificationResource> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationResource> notifications) {
        this.notifications = notifications;
    }
}
