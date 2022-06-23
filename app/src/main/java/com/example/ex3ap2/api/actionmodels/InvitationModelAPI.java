package com.example.ex3ap2.api.actionmodels;

public class InvitationModelAPI {
    public String from;
    public String to;
    public String server;

    public InvitationModelAPI(String from, String to, String server) {
        this.from = from;
        this.to = to;
        this.server = server;
    }
}
