package com.fuadhev.mytayqatask1.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fuadhev.mytayqatask1.ui.managment.activity.ManagementActivity;
import com.fuadhev.mytayqatask1.databinding.ActivityMainBinding;
import com.fuadhev.mytayqatask1.event.RemoteEvent;
import com.fuadhev.mytayqatask1.eventmanager.RemoteEventManager;

import org.greenrobot.eventbus.EventBus;

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