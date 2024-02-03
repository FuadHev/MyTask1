package com.fuadhev.mytayqatask1.controller;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.local.CompanyDao;
import com.fuadhev.mytayqatask1.data.local.UserDao;
import com.fuadhev.mytayqatask1.data.local.db.ObjectBox;

import java.util.List;

import io.objectbox.BoxStore;

public class DBController {


    public static void insertAllCompanies(CompanyEntity companyEntity ){
        CompanyDao.insertAll(companyEntity);
    }


    public static void insertAllUsers(UserEntity userEntity ){
        UserDao.insertAll(userEntity);
    }

    public static List<CompanyEntity> getAllCompany(){
        return CompanyDao.getAllCompany();
    }

    public static void attachCompany(CompanyEntity companyEntity) {
        CompanyDao.attachCompany(companyEntity);
    }

    public static void attachUser(UserEntity userEntity) {
        UserDao.attachUser(userEntity);
    }
}
