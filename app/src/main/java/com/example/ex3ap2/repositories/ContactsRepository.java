package com.example.ex3ap2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private ContactDao dao;
    private ContactListData contactListData;
    //private PostAPI api;

    public ContactsRepository() {

        AppData db = AppData.getInstance();
        dao = db.contactDao();
        contactListData = new ContactListData();
        //api = new PostAPI(postListData, dao);

    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add (final Contact contact) {
        dao.insert(contact);
        //api.add(contact);
    }

    public void delete (final Contact contact) {
        dao.delete(contact);
        contactListData.onActive();
        //api.delete(contact);
    }

    public void reload() {
        dao.index();
        contactListData.onActive();
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

            new Thread(() -> {
                contactListData.postValue(dao.index());
            }).start();
            //contactListData.setValue(dao.index());
        }
    }
}