package com.example.ex3ap2.viewmodels;

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

    public ContactsViewModel(User user) {
        contactsRepository = new ContactsRepository(user);
        contacts = contactsRepository.getAll();
    }

    public LiveData<List<Contact>> get() {
        return contacts;
    }

    public void add(Contact contact) {
        contactsRepository.add(contact);
    }

    public void delete(Contact contact) {
        contactsRepository.delete(contact);
    }

    public void reload() {
        contactsRepository.reload();
    }
}