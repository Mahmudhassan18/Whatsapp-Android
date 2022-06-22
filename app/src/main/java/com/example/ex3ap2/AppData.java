package com.example.ex3ap2;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.daos.UserDao;
import com.example.ex3ap2.typeconverters.ContactsConvertor;

@Database(entities = {User.class, Contact.class}, version = 6)
@TypeConverters({ContactsConvertor.class})
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
