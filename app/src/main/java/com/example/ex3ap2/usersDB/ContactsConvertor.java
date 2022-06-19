package com.example.ex3ap2.usersDB;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ContactsConvertor {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<User> stringToUsersList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<User>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String usersListToString(List<User> someObjects) {
        return gson.toJson(someObjects);
    }
}
