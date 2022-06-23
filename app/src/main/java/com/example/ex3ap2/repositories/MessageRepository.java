package com.example.ex3ap2.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.ex3ap2.AppData;
import com.example.ex3ap2.api.MessageAPI;
import com.example.ex3ap2.daos.MessageDao;
import com.example.ex3ap2.entities.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageRepository {
    private String contactUsername;
    private MessageDao dao;
    private MessageListData messageListData;
    private MessageAPI api;

    public MessageRepository(String contactUsername, String loggedUsername) {
        AppData db = AppData.getInstance();
        dao = db.messageDao();
        messageListData = new MessageRepository.MessageListData();
        api = new MessageAPI(messageListData, dao, contactUsername, loggedUsername);
        this.contactUsername = contactUsername;

        api.getAllMessagesOfContact();
    }

    public void reload() {
        api.getAllMessagesOfContact();
    }

    public class MessageListData extends MutableLiveData<List<Message>> {
        public MessageListData() {
            super();
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            messageListData.updateData();
        }

        public void updateData() {
            new Thread(() -> {
                messageListData.postValue(dao.getMessagesOfContact(contactUsername));
            }).start();
        }
    }
}
