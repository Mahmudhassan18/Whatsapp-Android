package com.example.ex3ap2.viewmodelfactories;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ex3ap2.viewmodels.ContactsViewModel;

public class ContactsViewModelFactory implements ViewModelProvider.Factory {
    private String usernameParam;

    public ContactsViewModelFactory(String usernameParam) {
        this.usernameParam = usernameParam;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ContactsViewModel(usernameParam);
    }
}
