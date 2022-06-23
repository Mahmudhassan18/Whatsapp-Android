package com.example.ex3ap2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.viewmodels.ContactsViewModel;
import com.example.ex3ap2.viewmodels.UsersViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private UsersViewModel viewModel;
    private List<User> users;

    /*
    private AppData appDB;
    private UserDao userDao;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        viewModel.get().observe(this, users -> {
            this.users = users;
        });

        Button signupLoginBtn = findViewById(R.id.signupLoginBtn);
        Button signupBtn = findViewById(R.id.signupBtn);
        signupLoginBtn.setOnClickListener(view -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
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
        if (users == null) {
            etError.setText("Loading data");
        }
        else if (username == null || nickname == null || password == null || rePassword == null){
            etError.setText(R.string.etNullField);
        }
        else if(!password.equals(rePassword)){
            etError.setText(R.string.etPasswordNotMatched);
        }
        else if(!pattern.matcher(password).matches()){
            etError.setText(R.string.etPasswordPattern);
        }
        else if(doesUsernameExist(username)){
            etError.setText(R.string.etUsernameUsed);
        }
        else {
            User user = new User(username, nickname, password);
            viewModel.add(user);
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    private boolean doesUsernameExist(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }
}