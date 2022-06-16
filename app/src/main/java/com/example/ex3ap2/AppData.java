package com.example.ex3ap2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppData extends RoomDatabase {
    public abstract UserDao userDao();
}
