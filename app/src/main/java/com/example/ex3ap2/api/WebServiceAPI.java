package com.example.ex3ap2.api;

import com.example.ex3ap2.api.actionmodels.ContactModelAPI;
import com.example.ex3ap2.api.actionmodels.InvitationModelAPI;
import com.example.ex3ap2.api.actionmodels.LoginModelAPI;
import com.example.ex3ap2.api.actionmodels.MessageModelAPI;
import com.example.ex3ap2.api.actionmodels.MessageContentModelAPI;
import com.example.ex3ap2.api.actionmodels.NewContactModelAPI;
import com.example.ex3ap2.api.actionmodels.SignupModelAPI;
import com.example.ex3ap2.api.actionmodels.TransferModelAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Path;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServiceAPI {
    @POST("users/login")
    Call<String> login(@Body LoginModelAPI loginModel);

    @POST("users/signup")
    Call<Void> signup(@Body SignupModelAPI signupModel);

    @GET("contacts")
    Call<List<ContactModelAPI>> getAllContacts(@Header("Authorization") String token);

    @POST("contacts")
    Call<Void> addContact(@Header("Authorization") String token, @Body NewContactModelAPI newContact);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Header("Authorization") String token, @Path("id") String contactUsername);

    @GET("contacts/{id}/messages")
    Call<List<MessageModelAPI>> getAllMessagesOfContact(@Header("Authorization") String token, @Path("id") String contactUsername);

    @POST("contacts/{id}/messages")
    Call<Void> sendMessage(@Header("Authorization") String token, @Path("id") String contactUsername, @Body MessageContentModelAPI messageContent);

    @POST("invitations")
    Call<Void> invite(@Header("Authorization") String token, @Body InvitationModelAPI invitationModel);

    @POST("transfer")
    Call<Void> transfer(@Header("Authorization") String token, @Body TransferModelAPI transferModel);
}
