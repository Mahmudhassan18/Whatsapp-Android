package com.example.ex3ap2.api;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.LoginActivity;
import com.example.ex3ap2.MyApplication;
import com.example.ex3ap2.R;
import com.example.ex3ap2.api.actionmodels.LoginModelAPI;
import com.example.ex3ap2.api.actionmodels.SignupModelAPI;
import com.example.ex3ap2.daos.UserDao;
import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.repositories.UsersRepository;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private UsersRepository.UserListData userListData;
    private UserDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public UserAPI(UsersRepository.UserListData userListData, UserDao dao) {
        this.userListData = userListData;
        this.dao = dao;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void login(String username, String password, AppCompatActivity loginActivity, Intent contactsIntent, TextView etError) {
        Call<String> call = webServiceAPI.login(new LoginModelAPI(username, password));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                MyApplication.token = response.body();
                if (dao.get(username) == null) {
                    dao.insert(new User(username, username, password));
                    userListData.updateData();
                }
                loginActivity.startActivity(contactsIntent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyApplication.token = "";
                etError.setText("Username or Password is incorrect");
            }
        });
    }

    public void signup(String username, String nickname, String password,
                       AppCompatActivity signupActivity, Intent loginIntent, TextView etError) {
        Call<Void> call = webServiceAPI.signup(new SignupModelAPI(username, nickname, password));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dao.insert(new User(username, nickname, password));
                userListData.updateData();
                signupActivity.startActivity(loginIntent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                etError.setText(R.string.etUsernameUsed);
            }
        });
    }
}
