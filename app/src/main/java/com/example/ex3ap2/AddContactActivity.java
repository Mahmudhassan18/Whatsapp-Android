package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;
import com.example.ex3ap2.viewmodels.ContactsViewModel;

import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ContactsViewModel contacstViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contacstViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        /*
        db = Room.databaseBuilder(getApplicationContext(), AppData.class, "UsersDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        userDao = db.userDao();*/

        Button addContactBtn = findViewById(R.id.addContactBtn);
        addContactBtn.setOnClickListener(view -> {
            EditText etUsername = findViewById(R.id.addContactUsername);
            EditText etNickname = findViewById(R.id.addContactNickname);
            EditText etServer = findViewById(R.id.addContactServer);

            String username = etUsername.getText().toString();
            String nickname = etNickname.getText().toString();
            String server = etServer.getText().toString();

            TextView errorTv = findViewById(R.id.tvAddContactError);

            if (username.equals("") || nickname.equals("") || server.equals("")) {
                errorTv.setText("You must enter all fields");
                return;
            }

            Contact contact = new Contact(username, nickname, server, "", "");
            contacstViewModel.add(contact);

            Intent contactsIntent = new Intent(this, ContactsActivity.class);
            startActivity(contactsIntent);

            /*EditText etUsername = findViewById(R.id.addContactUsername);
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
            }*/
        });
    }

    protected void addContact(@NonNull User loggedUser, @NonNull User userToAdd){
        /*loggedUser.addContact(userToAdd);
        userDao.update(loggedUser);
        userToAdd.addContact(loggedUser);
        userDao.update(userToAdd);//TODO UPDATE userToAdd (GETTING SOME KIND OF ERROR)
        List<User> contacts = loggedUser.getContacts();
        contacts.add(userToAdd);
        Intent contactsIntent = new Intent(this, ContactsActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", loggedUser.getUsername());
        contactsIntent.putExtras(bundle1);
        startActivity(contactsIntent);*/
    }
}