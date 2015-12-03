package com.example.deepak.myfriends;

import android.app.Application;

/**
 *
 */
public class MyFriendsApp extends Application {

    private static  MyFriendsApp instance;
    public static MyFriendsApp getInstance() {
        return  instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
