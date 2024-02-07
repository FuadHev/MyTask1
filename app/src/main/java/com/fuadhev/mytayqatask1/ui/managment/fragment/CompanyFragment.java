package com.fuadhev.mytayqatask1.ui.managment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fuadhev.mytayqatask1.R;
import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.databinding.FragmentCompanyBinding;
import com.fuadhev.mytayqatask1.event.LocalDbDataEvent;
import com.fuadhev.mytayqatask1.event.LocalDbEvent;
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


public class CompanyFragment extends Fragment implements CompanyItem.OnCompanyItemClickListener, UserItem.UpdateIsBlockMenuVisibility {


    private FragmentCompanyBinding binding;
    private CompanyAdapter adapter;

    private Menu myMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCompanyBinding.inflate(inflater, container, false);


        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbar);
        setAdapter();
        setHasOptionsMenu(true);
        EventBus.getDefault().post(new LocalDbEvent());


        return binding.getRoot();
    }


    private void setAdapter() {
        adapter = new CompanyAdapter(Collections.emptyList());
        binding.companyRv.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_toolbar, menu);
        myMenu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_block) {
            Log.e("menu", "onOptionsItemSelected: ");
            return true;
        }

        return super.onOptionsItemSelected(item);
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

                if (!user.getIsBlock()) {
                    Log.e("user", "userblack"+ user.getIsBlock());
                    userList.add(new UserItem(user, this));
                }
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


    @Override
    public void onCompanyItemClicked(int position) {

        if (adapter.isExpanded(position)) {
            adapter.collapse(position);
        } else {
            adapter.expand(position);
        }


    }


    @Override
    public void setBlockMenuVisibility() {
        Boolean anyBlocked = false;
        for (AbstractFlexibleItem item : adapter.getCurrentItems()) {
            if (anyBlocked) {
               break;
            }
            if (item instanceof CompanyItem) {
                Log.e("TAG", "setBlockMenuVisibility: itemuser"+(((CompanyItem) item).getCompanyItem()));
                List<UserItem> userItems= (((CompanyItem) item).getSubItems());
                for (UserItem useritem : userItems) {
                    if (useritem.getUserItem().getIsBlock()) {
                        anyBlocked = true;
                        break;
                    }
                }
            }
        }
        if (anyBlocked) {
            myMenu.findItem(R.id.menu_block).setVisible(true);
        } else {
            myMenu.findItem(R.id.menu_block).setVisible(false);
        }
    }
}














































