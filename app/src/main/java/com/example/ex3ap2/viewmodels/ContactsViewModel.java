package com.example.ex3ap2.viewmodels;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.repositories.ContactsRepository;
import com.example.ex3ap2.repositories.UsersRepository;

import java.util.List;

public class ContactsViewModel extends ViewModel {
    private ContactsRepository contactsRepository;
    private LiveData<List<Contact>> contacts;

    public ContactsViewModel(String username) {
        contactsRepository = new ContactsRepository(username);
        contacts = contactsRepository.getAll();
    }

    public LiveData<List<Contact>> get() {
        return contacts;
    }

    public void add(String contactUsername, String contactNickname, String server,
                    AppCompatActivity addContactActivity, Intent contactsIntent, TextView etError) {
        contactsRepository.add(contactUsername, contactNickname, server, addContactActivity, contactsIntent, etError);
    }

    public void delete(Contact contact) {
        contactsRepository.delete(contact);
    }

    public void reload() {
        contactsRepository.reload();
    }
}