package com.fuadhev.mytayqatask1.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.local.CompanyDao;
import com.fuadhev.mytayqatask1.ui.managment.activity.ManagementActivity;
import com.fuadhev.mytayqatask1.databinding.ActivityMainBinding;
import com.fuadhev.mytayqatask1.event.RemoteEvent;
import com.fuadhev.mytayqatask1.eventmanager.RemoteEventManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        CompanyDao.checkLocalData().observe(this, companyEntities -> {
            if (companyEntities.isEmpty()){
                Log.e("list", "empty: " );
                EventBus.getDefault().post(new RemoteEvent());
            }else {
                Log.e("list", "notempty " );
            }
        });


        binding.btnManagemenet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
                startActivity(intent);
            }
        });

        EventBus.getDefault().register(new RemoteEventManager());


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