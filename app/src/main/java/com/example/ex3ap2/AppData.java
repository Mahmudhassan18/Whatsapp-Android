package com.example.ex3ap2;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.daos.MessageDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.Message;
import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.daos.UserDao;

@Database(entities = {User.class, Contact.class, Message.class}, version = 6)
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
    public abstract MessageDao messageDao();
}
