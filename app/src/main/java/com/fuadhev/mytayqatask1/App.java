package com.fuadhev.mytayqatask1;

import android.app.Application;

import com.fuadhev.mytayqatask1.data.local.db.ObjectBox;

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
