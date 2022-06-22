package com.example.ex3ap2.typeconverters;

import androidx.room.TypeConverter;

import com.example.ex3ap2.entities.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ContactsConvertor {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Contact> stringToContactsList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Contact>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String contactsListToString(List<Contact> someObjects) {
        return gson.toJson(someObjects);
    }
}
