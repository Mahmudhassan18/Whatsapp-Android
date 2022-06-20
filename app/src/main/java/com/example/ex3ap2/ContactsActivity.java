package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ex3ap2.adapters.ContactsListAdapter;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;
import com.example.ex3ap2.viewmodels.ContactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ContactsViewModel viewModel;
    private ContactsListAdapter adapter;

    private AppData db;
    private UserDao userDao;
    private ArrayList<User> contacts;
    //private ArrayAdapter<User> adapter;
    private User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        adapter = new ContactsListAdapter(this, viewModel);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });

        FloatingActionButton addContactFloatingBtn = findViewById(R.id.addContactFloatingBtn);
        addContactFloatingBtn.setOnClickListener(view -> {
            Intent addContactIntent = new Intent(this, AddContactActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("username", "Empty for now");
            addContactIntent.putExtras(bundle2);
            startActivity(addContactIntent);
        });

        SwipeRefreshLayout swl = findViewById(R.id.contactsRefreshLayout);
        swl.setOnRefreshListener(() -> {
            viewModel.reload();
            swl.setRefreshing(false);
        });
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