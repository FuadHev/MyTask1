package com.fuadhev.mytayqatask1.data.local;

import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity_;
import com.fuadhev.mytayqatask1.data.local.db.ObjectBox;
import com.fuadhev.mytayqatask1.data.network.model.User;

import java.util.List;

import io.objectbox.Box;

public class UserDao {

    private static final Box<UserEntity> boxStore = ObjectBox.getBoxStore().boxFor(UserEntity.class);


    public static void insertAll(UserEntity userEntity) {
        boxStore.put(userEntity);
    }

    public static List<UserEntity> getBlockUsers() {
        return boxStore.query().equal(UserEntity_.isBlock, true).build().find();
    }

    public static void blockUsers(List<UserEntity> userEntities) {
        boxStore.put(userEntities);
    }

    public static List<UserEntity> getAllUser() {
        return boxStore.getAll();
    }

    public static void deleteBlockedUsers(List<UserEntity> deleteUserList){
        boxStore.remove(deleteUserList);
    }
}
