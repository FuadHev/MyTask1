package com.fuadhev.mytayqatask1.event;

import com.fuadhev.mytayqatask1.data.entity.UserEntity;

import java.util.List;

public class SetBlockUserEvent {

    private final List<UserEntity> userList;

    public SetBlockUserEvent(List<UserEntity> blockUserList) {
        this.userList=blockUserList;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }
}
