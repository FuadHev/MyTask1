package com.fuadhev.mytayqatask1.controller;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.local.CompanyDao;
import com.fuadhev.mytayqatask1.data.local.UserDao;

import java.util.List;

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
    public static List<UserEntity> getAllUser(){
        return UserDao.getAllUser();
    }

//    public static void attachCompany(CompanyEntity companyEntity) {
//        CompanyDao.attachCompany(companyEntity);
//    }

//    public static void attachUser(UserEntity userEntity) {
//        UserDao.attachUser(userEntity);
//    }

    public static List<UserEntity> getBlockUsers(){
        return UserDao.getBlockUsers();
    }

    public static void setBlockUsers(List<UserEntity> userEntities ){
        UserDao.blockUsers(userEntities);
    }

    public static void deleteBlockUsers(List<UserEntity> userList){
        UserDao.deleteBlockedUsers(userList);
    }
}
