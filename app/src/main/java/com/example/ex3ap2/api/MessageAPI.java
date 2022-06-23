package com.example.ex3ap2.api;

import com.example.ex3ap2.MyApplication;
import com.example.ex3ap2.R;
import com.example.ex3ap2.api.actionmodels.MessageContentModelAPI;
import com.example.ex3ap2.api.actionmodels.MessageModelAPI;
import com.example.ex3ap2.api.actionmodels.TransferModelAPI;
import com.example.ex3ap2.daos.MessageDao;
import com.example.ex3ap2.entities.Message;
import com.example.ex3ap2.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    private MessageRepository.MessageListData messageListData;
    private MessageDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String contactUsername;
    private String loggedUsername;

    public MessageAPI(MessageRepository.MessageListData messageListData, MessageDao dao, String contactUsername, String loggedUsername) {
        this.messageListData = messageListData;
        this.dao = dao;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.contactUsername = contactUsername;
        this.loggedUsername = loggedUsername;
    }

    public void getAllMessagesOfContact() {
        Call<List<MessageModelAPI>> call = webServiceAPI.getAllMessagesOfContact("Bearer " + MyApplication.token, contactUsername);
        call.enqueue(new Callback<List<MessageModelAPI>>() {
            @Override
            public void onResponse(Call<List<MessageModelAPI>> call, Response<List<MessageModelAPI>> response) {
                List<MessageModelAPI> messageModelsFromServer = response.body();
                List<Message> messagesFromRoom = dao.index();

                for (Message message : messagesFromRoom)
                    dao.delete(message);

                for (MessageModelAPI messageModel : messageModelsFromServer)
                    dao.insert(new Message(messageModel.content, messageModel.created, messageModel.sent, contactUsername));

                messageListData.updateData();
            }

            @Override
            public void onFailure(Call<List<MessageModelAPI>> call, Throwable t) {
            }
        });
    }

    public void sendMessage(String content) {
        Call<Void> call = webServiceAPI.sendMessage("Bearer " + MyApplication.token, contactUsername, new MessageContentModelAPI(content));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dao.insert(new Message(content, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()).toString(),
                        true, contactUsername));
                messageListData.updateData();
                webServiceAPI.transfer("Bearer " + MyApplication.token, new TransferModelAPI(loggedUsername, contactUsername, content));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
