package com.fuadhev.mytayqatask1.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fuadhev.mytayqatask1.databinding.ActivityMainBinding;
import com.fuadhev.mytayqatask1.event.LocalDbDataEvent;
import com.fuadhev.mytayqatask1.event.LocalDbEvent;
import com.fuadhev.mytayqatask1.event.RemoteEvent;
import com.fuadhev.mytayqatask1.eventmanager.RemoteEventManager;
import com.fuadhev.mytayqatask1.ui.managment.activity.ManagementActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //normalda bele yoxlayirdim bos olub olmamasini
//        CompanyDao.checkLocalData().observe(this, companyEntities -> {
//            if (companyEntities.isEmpty()) {
//                EventBus.getDefault().post(new RemoteEvent());
//            }
//        });


        binding.btnManagemenet.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            startActivity(intent);
        });

        EventBus.getDefault().register(new RemoteEventManager());
        EventBus.getDefault().register(this);

        EventBus.getDefault().post(new LocalDbEvent());


    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onEvent(LocalDbDataEvent event) {
        if (event.getCompanyList().isEmpty()) {
            EventBus.getDefault().post(new RemoteEvent());
            Log.e("onEvent", "onEvent");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(new RemoteEventManager());
        EventBus.getDefault().unregister(this);
    }
}