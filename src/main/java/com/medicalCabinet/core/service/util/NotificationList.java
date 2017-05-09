package com.medicalCabinet.core.service.util;

import com.medicalCabinet.core.models.Notification;

import java.util.ArrayList;

/**
 * Created by Andrada on 5/8/2017.
 */
public class NotificationList {
    private ArrayList<Notification> notifications ;

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}
