package com.fuadhev.mytayqatask1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.fuadhev.mytayqatask1.data.entity.MyObjectBox;
import com.fuadhev.mytayqatask1.data.local.db.ObjectBox;
import com.fuadhev.mytayqatask1.data.network.ApiService;
import com.fuadhev.mytayqatask1.data.network.RetrofitClient;
import com.fuadhev.mytayqatask1.data.network.model.Company;
import com.fuadhev.mytayqatask1.data.network.model.CompanyListResponse;
import com.fuadhev.mytayqatask1.databinding.ActivityMainBinding;
import com.fuadhev.mytayqatask1.event.RemoteEvent;
import com.fuadhev.mytayqatask1.eventmanager.RemoteEventManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());




        binding.btnManagemenet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
                startActivity(intent);
            }
        });

        EventBus.getDefault().register(new RemoteEventManager());

        EventBus.getDefault().post(new RemoteEvent());
    }


//    @Subscribe(threadMode = ThreadMode.ASYNC)
//    public void onEvent(RemoteEvent event) {
//        Log.e("event", "onEvent: " );
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(new RemoteEventManager());
    }
}