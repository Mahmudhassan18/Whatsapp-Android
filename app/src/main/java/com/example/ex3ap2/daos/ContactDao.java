package com.example.ex3ap2.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact Where id = :id")
    Contact get(int id);

    @Query("SELECT * FROM contact Where userId = :userId")
    List<Contact> getContactsOfUser(int userId);

    @Insert
    void insert(Contact... Contacts);

    @Update
    void update(Contact... Contacts);

    @Delete
    void delete(Contact... Contacts);
}
