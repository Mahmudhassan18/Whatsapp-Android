package com.example.ex3ap2.repositories;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.api.ContactAPI;
import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private String username;
    private ContactDao dao;
    private ContactListData contactListData;
    private ContactAPI api;

    public ContactsRepository(String username) {
        AppData db = AppData.getInstance();
        dao = db.contactDao();
        contactListData = new ContactListData();
        api = new ContactAPI(contactListData, dao, username);
        this.username = username;

        api.getAllContacts();
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add (String contactUsername, String contactNickname, String server,
                     AppCompatActivity addContactActivity, Intent contactsIntent, TextView etError) {
        api.addContact(contactUsername, contactNickname, server, addContactActivity, contactsIntent, etError);
    }

    public void delete (final Contact contact) {
        api.deleteContact(contact);
    }

    public void reload() {
        api.getAllContacts();
    }

    public class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            contactListData.updateData();
        }

        public void updateData() {
            new Thread(() -> {
                contactListData.postValue(dao.getContactsOfUser(username));
            }).start();
        }
    }
}