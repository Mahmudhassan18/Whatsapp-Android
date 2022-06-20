package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ex3ap2.adapters.ContactsListAdapter;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private AppData db;
    private UserDao userDao;
    private ArrayList<User> contacts;
    private ArrayAdapter<User> adapter;
    private User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> lst = new LinkedList<Contact>();
        lst.add(new Contact("hi", "HI", "idk", "gud", "time"));
        lst.add(new Contact("bye", "BYE", "idfk", "bad", "timing"));
        adapter.setContacts(lst);
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Bundle bundle = getIntent().getExtras();
        String loggedUsername = bundle.getString("username");

        db = Room.databaseBuilder(getApplicationContext(), AppData.class, "UsersDB")
                .fallbackToDestructiveMigration()
        .allowMainThreadQueries().build();
        userDao = db.userDao();

        FloatingActionButton addContactFloatingBtn = findViewById(R.id.addContactFloatingBtn);
        addContactFloatingBtn.setOnClickListener(view -> {
            Intent addContactIntent = new Intent(this, AddContactActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("username", loggedUsername);
            addContactIntent.putExtras(bundle);
            startActivity(addContactIntent);
        });

        loggedUser = userDao.get(loggedUsername);
        contacts = new ArrayList<>();

        ListView lvContacts = findViewById(R.id.lvContacts);
        adapter = new ContactsListAdapter(getApplicationContext(), contacts);
//        adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                contacts);

        lvContacts.setAdapter(adapter);

         lvContacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
             User contactToRemove = contacts.remove(i);
             loggedUser.removeContact(contactToRemove);
             contactToRemove.removeContact(loggedUser);
             userDao.update(loggedUser, contactToRemove);
             adapter.notifyDataSetChanged();
             return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(loggedUser.getContacts());
        adapter.notifyDataSetChanged();
    }*/
}