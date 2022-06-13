package com.example.ex3ap2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginSignupBtn = findViewById(R.id.loginSignupBtn);
        loginSignupBtn.setOnClickListener(view -> {
            Intent signUpIntent = new Intent(this, SignupActivity.class);
            startActivity(signUpIntent);
        });
    }
}