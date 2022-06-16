package com.example.ex3ap2.usersDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String username;
    private final String name;
    private final String password;
//    private List<User> contacts;


    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public int getId(){return id;}

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

//    public List<User> getContacts() {
//        return contacts;
//    }

//    public User getContactByUsername(String username){
//        for (User user: contacts) {
//            if (user.username.equals(username))
//                return user;
//        }
//        return null;
//    }

//    public void addContact(User contact){
//        if (!contacts.contains(contact)){
//            contacts.add(contact);
//        }
//    }

    @Override
    public String toString() {
        return "User{" +
                "Id =" + id +
                ", Username ='" + username + '\'' +
                '}';
    }
}
