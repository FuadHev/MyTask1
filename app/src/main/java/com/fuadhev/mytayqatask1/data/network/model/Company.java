package com.fuadhev.mytayqatask1.data.network.model;

import java.util.List;

public class Company {
    private int id;
    private String name;
    private List<User> userList;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}

