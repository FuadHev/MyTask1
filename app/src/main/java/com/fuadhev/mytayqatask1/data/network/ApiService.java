package com.fuadhev.mytayqatask1.data.network;

import com.fuadhev.mytayqatask1.data.network.model.CompanyListResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v1/897cedcd-5ec6-4b8f-b91a-c05a7ef43fb3")
    Call<CompanyListResponse> getData();
}
