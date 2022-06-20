package com.example.ex3ap2;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

@Database(entities = {User.class, Contact.class}, version = 6)
public abstract class AppData extends RoomDatabase {
    private static AppData appDataSingleton = null;

    public static AppData getInstance() {
        if (appDataSingleton == null) {
            appDataSingleton = Room.databaseBuilder(MyApplication.context, AppData.class, "WhatsappDB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return appDataSingleton;
    }

    public abstract UserDao userDao();
    public abstract ContactDao contactDao();
}
