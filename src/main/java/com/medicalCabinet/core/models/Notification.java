package com.medicalCabinet.core.models;



import javax.persistence.*;

/**
 * Created by Andrada on 5/8/2017.
 */
@Entity
@Table( name="notification")
public class Notification {
    private long id;
    private String message;
    private String username;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    @Column(name="username")
    public void setUsername(String username) {
        this.username = username;
    }
}
