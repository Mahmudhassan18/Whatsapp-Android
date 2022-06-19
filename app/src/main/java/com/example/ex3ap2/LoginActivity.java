package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

public class LoginActivity extends AppCompatActivity {

    private AppData appDB;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appDB = Room.databaseBuilder(getApplicationContext(), AppData.class, "UsersDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        userDao = appDB.userDao();

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

        User user = userDao.get(username);

        if (user != null && user.getPassword().equals(password)){
            Intent contactsIntent = new Intent(this, ContactsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            contactsIntent.putExtras(bundle);
            startActivity(contactsIntent);
        }
        else{
            tvLoginError.setText("Username or Password is incorrect");
        }
    }
}