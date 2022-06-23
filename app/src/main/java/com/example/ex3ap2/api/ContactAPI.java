package com.example.ex3ap2.api;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3ap2.MyApplication;
import com.example.ex3ap2.R;
import com.example.ex3ap2.api.actionmodels.ContactModelAPI;
import com.example.ex3ap2.api.actionmodels.NewContactModelAPI;
import com.example.ex3ap2.daos.ContactDao;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.repositories.ContactsRepository;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    private ContactsRepository.ContactListData contactListData;
    private ContactDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String username;

    public ContactAPI(ContactsRepository.ContactListData userListData, ContactDao dao, String username) {
        this.contactListData = userListData;
        this.dao = dao;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.username = username;
    }

    public void getAllContacts() {
        Call<List<ContactModelAPI>> call = webServiceAPI.getAllContacts("Bearer " + MyApplication.token);
        call.enqueue(new Callback<List<ContactModelAPI>>() {
            @Override
            public void onResponse(Call<List<ContactModelAPI>> call, Response<List<ContactModelAPI>> response) {
                List<ContactModelAPI> contactModelsFromServer = response.body();
                List<Contact> contactFromRoom = dao.index();

                for (Contact contact : contactFromRoom)
                    dao.delete(contact);

                for (ContactModelAPI contactModel : contactModelsFromServer)
                    dao.insert(new Contact(contactModel.id, contactModel.name, contactModel.server, contactModel.last, contactModel.lastdate, username));
            }

            @Override
            public void onFailure(Call<List<ContactModelAPI>> call, Throwable t) {
            }
        });
    }

    public void addContact(String contactUsername, String contactNickname, String server,
                           AppCompatActivity addContactActivity, Intent contactsIntent, TextView etError) {
        Call<Void> call = webServiceAPI.addContact("Bearer " + MyApplication.token, new NewContactModelAPI(contactUsername, contactNickname, server));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dao.insert(new Contact(contactUsername, contactNickname, server, null, null, username));
                contactListData.updateData();
                addContactActivity.startActivity(contactsIntent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                etError.setText("Error: Check if there already exists a contact with this username");
            }
        });
    }

    public void deleteContact(Contact contact) {
        Call<Void> call = webServiceAPI.deleteContact("Bearer " + MyApplication.token, contact.getUsername());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dao.delete(contact);
                contactListData.updateData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
