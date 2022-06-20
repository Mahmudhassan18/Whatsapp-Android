package com.example.ex3ap2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

@Database(entities = {User.class, Contact.class}, version = 6)
public abstract class AppData extends RoomDatabase {
    public abstract UserDao userDao();
    //public abstract ContactDao contactDao();
}
