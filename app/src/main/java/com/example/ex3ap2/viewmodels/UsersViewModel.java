package com.example.ex3ap2.viewmodels;

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

    public void add(User user) {
        mRepository.add(user);
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