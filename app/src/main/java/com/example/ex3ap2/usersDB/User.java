package com.example.ex3ap2.usersDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String username;
    private final String name;
    private final String password;
    @TypeConverters(ContactsConvertor.class)
    private List<User> contacts;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.contacts = new ArrayList<>();

    }

    public List<User> getContacts() {
        return contacts;
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

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public void addContact(User contact){
        contacts.add(contact);
    }

    public void removeContact(User contact){
        contacts.remove(contact);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id =" + id +
                ", Username ='" + username + '\'' +
                '}';
    }
}
