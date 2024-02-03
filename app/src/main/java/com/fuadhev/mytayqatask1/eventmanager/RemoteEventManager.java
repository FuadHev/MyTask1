package com.fuadhev.mytayqatask1.eventmanager;

import android.util.Log;

import com.fuadhev.mytayqatask1.controller.DBController;
import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.local.UserDao;
import com.fuadhev.mytayqatask1.data.network.ApiService;
import com.fuadhev.mytayqatask1.data.network.RetrofitClient;
import com.fuadhev.mytayqatask1.data.network.model.Company;
import com.fuadhev.mytayqatask1.data.network.model.CompanyListResponse;
import com.fuadhev.mytayqatask1.data.network.model.User;
import com.fuadhev.mytayqatask1.event.RemoteEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RemoteEventManager {
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getRemoteData(RemoteEvent event) {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<CompanyListResponse> call = apiService.getData();

        call.enqueue(new Callback<CompanyListResponse>() {
            @Override
            public void onResponse(Call<CompanyListResponse> call, Response<CompanyListResponse> response) {
                if (response.isSuccessful()) {

                    CompanyListResponse companyListResponse = response.body();

                    List<Company> companyList = companyListResponse.getCompanyList();

                    for (Company company : companyList) {
                        CompanyEntity companyEntity = new CompanyEntity(company.getId(), company.getName());
                        DBController.attachCompany(companyEntity);
                        ArrayList<UserEntity> userEntityList = new ArrayList<>();

                        ;
                        for (User user : company.getUserList()) {
                            UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getSurname());
                            userEntity.setCompany(companyEntity);
                            DBController.attachUser(userEntity);
                            DBController.insertAllUsers(userEntity);
                            userEntityList.add(userEntity);
                        }
                        companyEntity.setUserList(userEntityList);
                        DBController.insertAllCompanies(companyEntity);
                    }

//
//                    for (Company company : companyList) {
//                        ArrayList<UserEntity> userEntityList = new ArrayList<>();
//
//                        for (User user : company.getUserList()) {
//                            userEntityList.add(new UserEntity(user.getId(), user.getName(), user.getSurname()));
//                        }
//                        CompanyEntity companyEntity = new CompanyEntity(company.getId(), company.getName());
//                        companyEntity.getUserList().addAll(userEntityList);
//
//                        DBController.insertAllCompanies(companyEntity);
//                        for (User user : company.getUserList()) {
//                            DBController.insertAllUsers(new UserEntity(user.getId(), user.getName(), user.getSurname()));
//                        }
//                    }


                } else {
                    try {
                        Log.e("API_ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CompanyListResponse> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });
    }


}