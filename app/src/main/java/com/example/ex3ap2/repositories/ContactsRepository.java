package com.example.ex3ap2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.entities.User;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private User user;
    private ContactDao dao;
    private ContactListData contactListData;
    //private PostAPI api;

    public ContactsRepository(User user) {
        AppData db = AppData.getInstance();
        dao = db.contactDao();
        contactListData = new ContactListData();
        //api = new PostAPI(postListData, dao);

        this.user = user;
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add (final Contact contact) {
        dao.insert(contact);
        contactListData.updateData();
        //api.add(contact);
    }

    public void delete (final Contact contact) {
        dao.delete(contact);
        contactListData.updateData();
        //api.delete(contact);
    }

    public void reload() {
        contactListData.updateData();
        //api.get();
    }

    class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            setValue(new LinkedList<>());

            /*List<Contact> lst = new LinkedList<Contact>();
            lst.add(new Contact("hi", "HI", "idk", "gud", "time"));
            lst.add(new Contact("bye", "BYE", "idfk", "bad", "timing"));
            setValue(lst);*/
        }

        @Override
        protected void onActive() {
            super.onActive();
            contactListData.updateData();
        }

        private void updateData() {
            new Thread(() -> {
                contactListData.postValue(dao.getContactsOfUser(user.getId()));
            }).start();
        }
    }
}