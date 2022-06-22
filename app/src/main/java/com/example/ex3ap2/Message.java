package com.example.ex3ap2;

import com.example.ex3ap2.entities.User;

import java.util.Date;

public class Message {
    private User sentFrom;
    private User sentTo;
    private String message;
    private Date date;

    public Message(User sentFrom, User sentTo, String message, Date date) {
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.message = message;
        this.date = date;
    }

    public User getSentFrom() {
        return sentFrom;
    }

    public User getSentTo() {
        return sentTo;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
