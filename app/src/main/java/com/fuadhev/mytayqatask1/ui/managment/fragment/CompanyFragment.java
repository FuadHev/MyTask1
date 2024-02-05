package com.fuadhev.mytayqatask1.ui.managment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.databinding.FragmentBlockedBinding;
import com.fuadhev.mytayqatask1.databinding.FragmentCompanyBinding;
import com.fuadhev.mytayqatask1.event.LocalDbDataEvent;
import com.fuadhev.mytayqatask1.event.LocalDbEvent;
import com.fuadhev.mytayqatask1.eventmanager.RemoteEventManager;
import com.fuadhev.mytayqatask1.ui.managment.fragment.adapter.CompanyAdapter;
import com.fuadhev.mytayqatask1.ui.model.CompanyItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class CompanyFragment extends Fragment {


    private FragmentCompanyBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCompanyBinding.inflate(inflater, container, false);
        //adapter = new CompanyAdapter(null, false, true);

        EventBus.getDefault().post(new LocalDbEvent());

        return binding.getRoot();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocalDbDataEvent event) {
        Log.e("company", event.getCompanyList().toString());
        List<CompanyItem> newList = new ArrayList<>();
        for (CompanyEntity companyEntity : event.getCompanyList()) {
            Log.e("company", companyEntity.getName());
            newList.add(new CompanyItem(companyEntity));
//            companyEntity.getUserList().size();
            for (UserEntity user : companyEntity.getUserList()) {
                Log.e("user", user.getName());
            }
        }
        //adapter.upd
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        binding = null;
    }

}