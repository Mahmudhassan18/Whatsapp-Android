package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signupLoginBtn = findViewById(R.id.signupLoginBtn);
        signupLoginBtn.setOnClickListener(view -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }
}