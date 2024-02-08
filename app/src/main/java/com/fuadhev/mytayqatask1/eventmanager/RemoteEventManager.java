package com.fuadhev.mytayqatask1.eventmanager;

import android.util.Log;

import com.fuadhev.mytayqatask1.controller.DBController;
import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.network.ApiService;
import com.fuadhev.mytayqatask1.data.network.RetrofitClient;
import com.fuadhev.mytayqatask1.data.network.model.Company;
import com.fuadhev.mytayqatask1.data.network.model.CompanyListResponse;
import com.fuadhev.mytayqatask1.data.network.model.User;
import com.fuadhev.mytayqatask1.event.BlockUserEvent;
import com.fuadhev.mytayqatask1.event.DeleteUserEvent;
import com.fuadhev.mytayqatask1.event.GetBlockUserEvent;
import com.fuadhev.mytayqatask1.event.SetBlockUserEvent;
import com.fuadhev.mytayqatask1.event.LocalDbDataEvent;
import com.fuadhev.mytayqatask1.event.LocalDbEvent;
import com.fuadhev.mytayqatask1.event.RemoteEvent;

import org.greenrobot.eventbus.EventBus;
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

                        DBController.insertAllCompanies(companyEntity);
                        List<UserEntity> userlist = new ArrayList<>();

                        for (User user : company.getUserList()) {
                            UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getSurname(), false);
                            userEntity.setCompany(companyEntity);
                            companyEntity.getUserList().add(userEntity);
                            DBController.insertAllUsers(userEntity);
                        }
                        DBController.insertAllCompanies(companyEntity);
                    }

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

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getLocalData(LocalDbEvent event) {
        List<CompanyEntity> companyList = DBController.getAllCompany();
        EventBus.getDefault().post(new LocalDbDataEvent(companyList));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void blockUser(SetBlockUserEvent event) {
        DBController.setBlockUsers(event.getUserList());
        EventBus.getDefault().post(new LocalDbEvent());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getBlockUser(GetBlockUserEvent event) {
        List<UserEntity> userList = DBController.getBlockUsers();
        EventBus.getDefault().post(new BlockUserEvent(userList));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void deleteUsers(DeleteUserEvent event) {
        List<UserEntity> userList = event.getUserList();
        DBController.deleteBlockUsers(userList);
        EventBus.getDefault().post(new GetBlockUserEvent());
    }


}