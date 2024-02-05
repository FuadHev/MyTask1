package com.fuadhev.mytayqatask1.event;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;

import java.util.List;

public class LocalDbDataEvent {

    private final List<CompanyEntity> companyList;

    public  LocalDbDataEvent(List<CompanyEntity> companyList){
        this.companyList=companyList;
    }

    public List<CompanyEntity> getCompanyList() {
        return companyList;
    }
}
