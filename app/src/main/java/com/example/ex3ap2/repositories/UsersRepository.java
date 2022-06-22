package com.example.ex3ap2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.daos.UserDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;

import java.util.LinkedList;
import java.util.List;

public class UsersRepository {
    private UserDao dao;
    private UserListData userListData;
    //private PostAPI api;

    public UsersRepository() {

        AppData db = AppData.getInstance();
        dao = db.userDao();
        userListData = new UserListData();
        //api = new PostAPI(postListData, dao);

    }

    public LiveData<List<User>> getAll() {
        return userListData;
    }

    public User get(String username) {
        return dao.get(username);
    }

    public void add (final User user) {
        dao.insert(user);
        userListData.updateData();
        //api.add(contact);
    }

    /*public void delete (final User user) {
        dao.delete(user);
        //api.delete(contact);
    }*/

    /*public void reload() {
        dao.index();
        //api.get();
    }*/

    public void addContactToUser(final User user, final Contact contact) {
        user.getContacts().add(contact);
        dao.update(user);
        userListData.updateData();
    }

    public void deleteContactOfUser(final User user, final Contact contact) {
        user.getContacts().remove(contact);
        dao.update(user);
        userListData.updateData();
    }

    class UserListData extends MutableLiveData<List<User>> {
        public UserListData() {
            super();
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            userListData.updateData();
        }

        private void updateData() {
            new Thread(() -> {
                userListData.postValue(dao.index());
            }).start();
        }
    }
}