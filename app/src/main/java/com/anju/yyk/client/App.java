package com.anju.yyk.client;

import android.app.Application;

import com.anju.yyk.client.util.SPUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.init(this);
    }
}
