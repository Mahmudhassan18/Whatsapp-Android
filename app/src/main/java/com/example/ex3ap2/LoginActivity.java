package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.daos.UserDao;
import com.example.ex3ap2.viewmodels.UsersViewModel;

import java.util.LinkedList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private UsersViewModel viewModel;

    /*
    private AppData appDB;
    private UserDao userDao;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        Button loginSignupBtn = findViewById(R.id.loginSignupBtn);
        Button loginBtn = findViewById(R.id.loginBtn);

        loginSignupBtn.setOnClickListener(view -> {
            Intent signUpIntent = new Intent(this, SignupActivity.class);
            startActivity(signUpIntent);
        });

        loginBtn.setOnClickListener(view -> {
            login();
        });

    }

    private void login(){
        EditText etUsername = findViewById(R.id.etLoginUsername);
        EditText etPassword = findViewById(R.id.etLoginPassword);
        TextView tvLoginError = findViewById(R.id.etLoginError);

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Intent contactsIntent = new Intent(this, ContactsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        contactsIntent.putExtras(bundle);
        viewModel.login(username, password, this, contactsIntent, tvLoginError);
    }
}