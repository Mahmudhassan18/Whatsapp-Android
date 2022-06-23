package com.example.ex3ap2.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;
    
    private String created;
    
    public boolean sent;

    private String contactUsername;

    public Message(String content, String created, boolean sent, String contactUsername) {
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.contactUsername = contactUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getContactUsername() {
        return contactUsername;
    }

    public void setContactUsername(String contactUsername) {
        this.contactUsername = contactUsername;
    }
}
