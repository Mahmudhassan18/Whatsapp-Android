package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ex3ap2.usersDB.User;
import com.example.ex3ap2.usersDB.UserDao;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private AppData appDB;
    private UserDao userDao;
    private Intent loginIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        appDB = Room.databaseBuilder(getApplicationContext(), AppData.class, "UsersDB")
        .allowMainThreadQueries().build();
        userDao = appDB.userDao();

        Button signupLoginBtn = findViewById(R.id.signupLoginBtn);
        Button signupBtn = findViewById(R.id.signupBtn);
        signupLoginBtn.setOnClickListener(view -> {
            loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });

        signupBtn.setOnClickListener(this::signup);
    }

    private void signup(View view){
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etNickname = findViewById(R.id.etNickname);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etRePassword = findViewById(R.id.etConfirmPassword);

        String username = etUsername.getText().toString();
        String nickname = etNickname.getText().toString();
        String password = etPassword.getText().toString();
        String rePassword = etRePassword.getText().toString();

        confirmSignup(username, nickname, password, rePassword);

    }

    private void confirmSignup(String username, String nickname, String password, String rePassword){
        TextView etError = findViewById(R.id.etSignupError);
        String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (username == null || nickname == null || password == null || rePassword == null){
            etError.setText(R.string.etNullField);
        }
        else if(!password.equals(rePassword)){
            etError.setText(R.string.etPasswordNotMatched);
        }
        else if(!pattern.matcher(password).matches()){
            etError.setText(R.string.etPasswordPattern);
        }
        else if(userDao.get(username) != null){
            etError.setText(R.string.etUsernameUsed);
        }
        else{
            User user = new User(username, nickname, password);
            userDao.insert(user);
            startActivity(loginIntent);
        }
    }
}