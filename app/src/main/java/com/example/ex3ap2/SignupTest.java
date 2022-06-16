package com.example.ex3ap2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

import java.util.ArrayList;
import java.util.List;

public class SignupTest extends AppCompatActivity {

      private AppData db;
      private UserDao userDao;
      private List<User> users;
      private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_test);
        db = Room.databaseBuilder(getApplicationContext(), AppData.class, "UsersDB")
        .allowMainThreadQueries().build();
        userDao = db.userDao();

        users = new ArrayList<>();
        ListView lvUsers = findViewById(R.id.lvUsers);
        adapter = new ArrayAdapter<User>(this,
                  android.R.layout.simple_list_item_1,
                  users);

        lvUsers.setAdapter(adapter);

        lvUsers.setOnItemLongClickListener((adapterView, view, i, l) -> {
             User post = users.remove(i);
             userDao.delete(post);
             adapter.notifyDataSetChanged();
             return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        users.clear();
        users.addAll(userDao.index());
        adapter.notifyDataSetChanged();
    }
}