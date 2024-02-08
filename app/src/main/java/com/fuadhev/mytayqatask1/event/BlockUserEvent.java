package com.fuadhev.mytayqatask1.event;

import com.fuadhev.mytayqatask1.data.entity.UserEntity;

import java.util.List;

public class BlockUserEvent {

    private final List<UserEntity> userList;

    public BlockUserEvent(List<UserEntity> blockUserList) {
        this.userList=blockUserList;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }
}
