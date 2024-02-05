package com.fuadhev.mytayqatask1.ui.managment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

import com.fuadhev.mytayqatask1.databinding.ActivityManagementBinding;
import com.fuadhev.mytayqatask1.ui.managment.fragment.BlockedFragment;
import com.fuadhev.mytayqatask1.ui.managment.fragment.CompanyFragment;

import java.util.ArrayList;

public class ManagementActivity extends AppCompatActivity {

    private ActivityManagementBinding binding;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> fragmentTitleList = new ArrayList<>();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(this);
        fragmentList.add(new CompanyFragment());
        fragmentList.add(new BlockedFragment());

        // add the fragments

//        List<CompanyEntity> list= DBController.getAllCompany();
//        for(CompanyEntity companyEntity : list){
//            Log.e("company",  companyEntity.getName());
//            companyEntity.getUserList().size();
//            for (UserEntity user:companyEntity.getUserList()){
//                Log.e("user", user.getName());
//            }
//        }
//        List<UserEntity> userList= DBController.getAllUser();
//        for(UserEntity user : userList){
//            Log.e("user", user.getCompany().toString());
//        }


        binding.viewPager.setAdapter(adapter);

    }
    public class MyViewPagerAdapter extends FragmentStateAdapter {

        public MyViewPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }
    }

}