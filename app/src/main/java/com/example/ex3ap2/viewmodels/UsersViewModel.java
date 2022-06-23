package com.example.ex3ap2.viewmodels;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.repositories.UsersRepository;

import java.util.List;

public class UsersViewModel extends ViewModel {
    private UsersRepository mRepository;
    private LiveData<List<User>> users;

    public UsersViewModel() {
        mRepository = new UsersRepository();
        users = mRepository.getAll();
    }

    public LiveData<List<User>> get() {
        return users;
    }

    public void signup(String username, String nickname, String password,
                       AppCompatActivity signupActivity, Intent loginIntent, TextView etError) {
        mRepository.signup(username, nickname, password, signupActivity, loginIntent, etError);
    }

    public void login(String username, String password, AppCompatActivity loginActivity, Intent contactsIntent, TextView etError) {
        mRepository.login(username, password, loginActivity, contactsIntent, etError);
    }

    public User getUserByUsername(String username) {
        return mRepository.get(username);
    }

    /*public void delete(User user) {
        mRepository.delete(user);
    }*/

    /*public void reload() {
        mRepository.reload();
    }*/
}