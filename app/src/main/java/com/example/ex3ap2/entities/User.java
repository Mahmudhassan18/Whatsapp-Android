package com.example.ex3ap2.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ex3ap2.typeconverters.ContactsConvertor;

import java.util.List;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String username;

    private final String name;

    private final String password;

    @TypeConverters(ContactsConvertor.class)
    private List<Contact> contacts;

    public User(String username, String name, String password, List<Contact> contacts) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
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

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void removeContact(Contact contact){
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
