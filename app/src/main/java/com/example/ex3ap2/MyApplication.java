package com.example.ex3ap2;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    public static String token;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        token = "";
    }
}
