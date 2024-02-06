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
import com.fuadhev.mytayqatask1.data.network.model.User;
import com.fuadhev.mytayqatask1.databinding.FragmentBlockedBinding;
import com.fuadhev.mytayqatask1.databinding.FragmentCompanyBinding;
import com.fuadhev.mytayqatask1.event.LocalDbDataEvent;
import com.fuadhev.mytayqatask1.event.LocalDbEvent;
import com.fuadhev.mytayqatask1.eventmanager.RemoteEventManager;
import com.fuadhev.mytayqatask1.ui.managment.fragment.adapter.CompanyAdapter;
import com.fuadhev.mytayqatask1.ui.model.CompanyItem;
import com.fuadhev.mytayqatask1.ui.model.UserItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;


public class CompanyFragment extends Fragment implements CompanyItem.OnCompanyItemClickListener  {


    private FragmentCompanyBinding binding;
    private CompanyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCompanyBinding.inflate(inflater, container, false);
        adapter = new CompanyAdapter(Collections.emptyList());
//        adapter.setDisplayHeadersAtStartUp(true)
//                .setStickyHeaders(true)
//                .setAutoCollapseOnExpand(false)
//                .setAutoScrollOnExpand(true);
//        adapter.mItemClickListener = this;
        binding.companyRv.setAdapter(adapter);
        EventBus.getDefault().post(new LocalDbEvent());

        return binding.getRoot();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocalDbDataEvent event) {
        Log.e("company", event.getCompanyList().toString());
        List<AbstractFlexibleItem> newList = new ArrayList<>();
        for (CompanyEntity companyEntity : event.getCompanyList()) {
            Log.e("company", companyEntity.getName());
            CompanyItem companyItem = new CompanyItem(companyEntity, this);
            List<UserItem> userList = new ArrayList<>();

            for (UserEntity user : companyEntity.getUserList()) {
                Log.e("user", user.getName());
                userList.add(new UserItem(user));
            }

            companyItem.setSubItems(userList);
            newList.add(companyItem);
        }
        adapter.updateDataSet(newList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        binding = null;
    }


  /*  @Override
    public boolean onItemClick(View view, int position) {
        Log.d("Fuad01", "onItemClick size: " + adapter.getCurrentItems().size());
        Log.d("Fuad03", "onItemClick pos: " + position);

        if (adapter.getItem(position) == null) return false;

        if (adapter.isExpanded(position)) {
            Log.d("Fuad02", "onItemClick isExpanded: " + adapter.isExpanded(position));
            adapter.collapse(position);
            Log.d("Fuad07", "onItemClick: inside if: " + adapter.isExpanded(position));
        }
        else {
            Log.d("Fuad08", "onItemClick: +++++++");
            adapter.expand(position);
        }

        return false;
    }*/

    @Override
    public void onCompanyItemClicked(int position) {

        if (adapter.isExpanded(position)) {
            adapter.collapse(position);
        }
        else {
            adapter.expand(position);
        }
    }
}














































