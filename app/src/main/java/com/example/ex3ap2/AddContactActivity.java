package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private AppData db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppData.class, "UsersDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        userDao = db.userDao();

        Button addContactBtn = findViewById(R.id.addContactBtn);
        addContactBtn.setOnClickListener(view -> {
            EditText etUsername = findViewById(R.id.addContactUsername);
            TextView errorTv = findViewById(R.id.tvAddContactError);
            String username = etUsername.getText().toString();
            User userToAdd = userDao.get(username);
            Bundle bundle = getIntent().getExtras();
            User loggedUser = userDao.get(bundle.getString("username")) ;
            if (userToAdd == null){
                errorTv.setText("User cannot be found");
            }
            else if (loggedUser.getContacts().contains(userToAdd)){
                errorTv.setText("User already in contacts");
            }
            else{
                addContact(loggedUser, userToAdd);
            }
        });
    }

    protected void addContact(@NonNull User loggedUser, @NonNull User userToAdd){
        loggedUser.addContact(userToAdd);
        userDao.update(loggedUser);
        userToAdd.addContact(loggedUser);
        userDao.update(userToAdd);//TODO UPDATE userToAdd (GETTING SOME KIND OF ERROR)
        List<User> contacts = loggedUser.getContacts();
        contacts.add(userToAdd);
        Intent contactsIntent = new Intent(this, ContactsActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", loggedUser.getUsername());
        contactsIntent.putExtras(bundle1);
        startActivity(contactsIntent);
    }
}