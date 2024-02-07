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

    private Boolean isBlock=false;

    public ToOne<CompanyEntity> company;
    public Boolean getIsBlock() {
        return isBlock;
    }
    public void setBlock(Boolean block) {
        isBlock = block;
    }


    public void setCompany(CompanyEntity company) {
        this.company.setTarget(company);
    }

    public CompanyEntity getCompany() {
        return company.getTarget();
    }

    public UserEntity() {

    }

    public UserEntity(long id, String name, String surname,Boolean isBlock) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isBlock=isBlock;
    }

    public void setId(long id) {
        this.id = id;
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
                ", isBlock=" + isBlock +
                ", company=" + company +
                '}';
    }

    public String getSurname() {
        return surname;
    }
}
