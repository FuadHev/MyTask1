package com.fuadhev.mytayqatask1.data.entity;

import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class CompanyEntity {
    @Id(assignable = true)
    private long id;
    private String name;
    @Backlink(to = "company")
    private ToMany<UserEntity> userList;

    public CompanyEntity() {

    }

    public CompanyEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ToMany<UserEntity> getUserList() {
        return this.userList;
    }

    // toString method
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList.size() +
                '}';
    }
}
