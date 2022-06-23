package com.example.ex3ap2.repositories;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.api.UserAPI;
import com.example.ex3ap2.daos.UserDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;

import java.util.LinkedList;
import java.util.List;

public class UsersRepository {
    private UserDao dao;
    private UserListData userListData;
    private UserAPI api;

    public UsersRepository() {

        AppData db = AppData.getInstance();
        dao = db.userDao();
        userListData = new UserListData();
        api = new UserAPI(userListData, dao);

    }

    public LiveData<List<User>> getAll() {
        return userListData;
    }

    public User get(String username) {
        return dao.get(username);
    }

    public void signup (String username, String nickname, String password,
                        AppCompatActivity signupActivity, Intent loginIntent, TextView etError) {
        api.signup(username, nickname, password, signupActivity, loginIntent, etError);
    }

    public void login(String username, String password, AppCompatActivity loginActivity, Intent contactsIntent, TextView etError) {
        api.login(username, password, loginActivity, contactsIntent, etError);
    }

    public class UserListData extends MutableLiveData<List<User>> {
        public UserListData() {
            super();
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            userListData.updateData();
        }

        public void updateData() {
            new Thread(() -> {
                userListData.postValue(dao.index());
            }).start();
        }
    }
}