package com.fuadhev.mytayqatask1.data.local.db;

import android.content.Context;

import com.fuadhev.mytayqatask1.data.entity.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBox {

    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder().androidContext(context.getApplicationContext()).build();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }
}
