package com.example.ex3ap2.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String username;

    private final String name;

    private final String password;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public int getId(){ return id; }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id){ this.id = id; }

    @Override
    public String toString() {
        return "User{" +
                "Id =" + id +
                ", Username ='" + username + '\'' +
                '}';
    }
}
