package com.fuadhev.mytayqatask1.data.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class UserEntity {
    @Id(assignable = true)
    private long id;
    private String name;
    private String surname;


    public ToOne<CompanyEntity> company;

    public void setCompany(CompanyEntity company) {
        this.company.setTarget(company);
    }

    public CompanyEntity getCompany() {
        return company.getTarget();
    }

    public UserEntity() {

    }

    public UserEntity(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", company=" + company.getTarget() +
                '}';
    }

    public String getSurname() {
        return surname;
    }
}
