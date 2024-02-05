package com.fuadhev.mytayqatask1.event;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;

import java.util.List;

public class LocalDbEvent {
    private List<CompanyEntity> companyList;

    public  LocalDbEvent(){
//        this.companyList=companyList;
    }

    public void setCompanyList(List<CompanyEntity> companyList) {
        this.companyList = companyList;
    }

    public List<CompanyEntity> getCompanyList() {
        return companyList;
    }
}
