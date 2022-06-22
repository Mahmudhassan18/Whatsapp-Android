package com.example.ex3ap2.viewmodelfactories;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ex3ap2.entities.User;
import com.example.ex3ap2.viewmodels.ContactsViewModel;

public class ContactsViewModelFactory implements ViewModelProvider.Factory {
    private User userParam;

    public ContactsViewModelFactory(User userParam) {
        this.userParam = userParam;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ContactsViewModel(userParam);
    }
}
