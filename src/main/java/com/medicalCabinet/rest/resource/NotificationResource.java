package com.medicalCabinet.rest.resource;

import com.medicalCabinet.core.models.Notification;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Andrada on 5/8/2017.
 */
public class NotificationResource extends ResourceSupport {
    private String message;
    private Long rid;
    private String username;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRid() {
        return rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Notification toNotification()
    {
        Notification notif = new Notification();
        notif.setMessage(message);
        notif.setUsername(username);
        return notif;
    }
}
