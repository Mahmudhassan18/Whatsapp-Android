package com.example.ex3ap2.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ex3ap2.R;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String nickname;

    private String server;

    private String last;

    private String lastdate;

    private int pic;

    private String user;

    public Contact(String username, String nickname, String server, String last, String lastdate, String user) {
        this.username = username;
        this.nickname = nickname;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.pic = R.drawable.ic_defaultimage;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; }

    public String getUsername() {
        return username; }

    public void setUsername(String username) {
        this.username = username; }

    public String getNickname() {
        return nickname; }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String userId) {
        this.user = user;
    }
}
