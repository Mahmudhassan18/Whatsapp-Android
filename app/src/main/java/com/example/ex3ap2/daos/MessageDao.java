package com.example.ex3ap2.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ex3ap2.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message Where id = :id")
    Message get(int id);

    @Query("SELECT * FROM message Where contactUsername = :contactUsername")
    List<Message> getMessagesOfContact(String contactUsername);

    @Insert
    void insert(Message... Messages);

    @Update
    void update(Message... Messages);

    @Delete
    void delete(Message... Messages);

}
