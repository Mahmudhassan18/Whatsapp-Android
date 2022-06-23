package com.example.ex3ap2.api;

import com.example.ex3ap2.api.actionmodels.ContactAPI;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Path;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServiceAPI {/*
    @GET("contacts")
    Call<List<ContactAPI>> getAllContacts();

    @POST("contacts")
    Call<Void> addContact(@Body NewContactModelAPI newContact);

    @GET("contacts/{id}")
    Call<ContactAPI> getContact(@Path("id") String contactUsername);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") String contactUsername);

    @GET("contacts/{id}/messages")
    Call<List<MessageAPI>> getAllMessageOfContact(@Path("id") String contactUsername);

    @POST("contacts/{id}/messages")
    Call<Void> addMessage(@Path("id") String contactUsername, @Body MessageContentModelAPI messageContent);

    @POST("invitations")
    Call<Void> invite(@Body InvitationModelAPI invitationModel);

    @POST("transfer")
    Call<Void> invite(@Body TransferModelAPI transferModel);*/
}
