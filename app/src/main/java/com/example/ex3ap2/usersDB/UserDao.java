package com.example.ex3ap2.usersDB;import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> index();

    @Query("SELECT * FROM user Where username = :username")
    User get(String username);

    @Insert
    void insert(User... Users);

    @Update
    void update(User... Users);

    @Delete
    void delete(User... Users);
}
