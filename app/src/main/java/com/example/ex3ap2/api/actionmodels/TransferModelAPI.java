package com.example.ex3ap2.api.actionmodels;

public class TransferModelAPI {
    public String from;
    public String to;
    public String content;

    public TransferModelAPI(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}
