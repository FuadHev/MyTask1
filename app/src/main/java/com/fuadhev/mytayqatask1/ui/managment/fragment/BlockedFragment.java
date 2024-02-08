package com.fuadhev.mytayqatask1.ui.managment.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.network.model.User;
import com.fuadhev.mytayqatask1.databinding.FragmentBlockedBinding;
import com.fuadhev.mytayqatask1.event.BlockUserEvent;
import com.fuadhev.mytayqatask1.event.DeleteUserEvent;
import com.fuadhev.mytayqatask1.event.GetBlockUserEvent;
import com.fuadhev.mytayqatask1.ui.managment.fragment.adapter.BlockUserAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class BlockedFragment extends Fragment implements BlockUserAdapter.OnUserCLickListener {

    private FragmentBlockedBinding binding;
    private BlockUserAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBlockedBinding.inflate(inflater, container, false);
        adapter = new BlockUserAdapter(requireContext(), Collections.emptyList(), this);
        binding.rvBlockUser.setAdapter(adapter);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.blockToolbar);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_delete, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_delete) {
            List<UserEntity> blockUserList = adapter.getCurrentList();
            List<UserEntity> deletedUser = blockUserList.stream()
                    .filter(user -> !user.getIsBlock())
                    .collect(Collectors.toList());
            showConfirmationDialog(deletedUser);

        }
        return super.onOptionsItemSelected(item);
    }

    private void showConfirmationDialog(List<UserEntity> deleteUserList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Bundan eminmisiniz?")
                .setPositiveButton("He", (dialog, id) -> {
                    EventBus.getDefault().post(new DeleteUserEvent(deleteUserList));
                    binding.blockToolbar.getMenu().findItem(R.id.menu_delete).setVisible(false);
                    dialog.dismiss();
                })
                .setNegativeButton("Yox", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new GetBlockUserEvent());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBlockUsers(BlockUserEvent event) {
        adapter.updateUserList(event.getUserList());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onCheckBoxClickListener() {
        List<UserEntity> blockUserList = adapter.getCurrentList();
        Boolean checkUsers = false;
        for (UserEntity user : blockUserList) {
            if (!user.getIsBlock()) {
                checkUsers = true;
                break;
            }
        }
        if (checkUsers) binding.blockToolbar.getMenu().findItem(R.id.menu_delete).setVisible(true);
        else binding.blockToolbar.getMenu().findItem(R.id.menu_delete).setVisible(false);
    }
}