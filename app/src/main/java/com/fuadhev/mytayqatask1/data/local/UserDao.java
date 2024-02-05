package com.fuadhev.mytayqatask1.data.local;

import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.local.db.ObjectBox;

import java.util.List;

import io.objectbox.Box;

public class UserDao {

    private static final Box<UserEntity> boxStore= ObjectBox.getBoxStore().boxFor(UserEntity.class);


    public static void insertAll(UserEntity userEntity){
        boxStore.put(userEntity);
    }
    public static void attachUser(UserEntity userEntity) {
        boxStore.attach(userEntity);

    }

    public static List<UserEntity> getAllUser(){
        return boxStore.getAll();
    }
}
