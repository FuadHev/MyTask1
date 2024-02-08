package com.fuadhev.mytayqatask1.ui.managment.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuadhev.mytayqatask1.R;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.network.model.User;

import java.util.List;

public class BlockUserAdapter extends RecyclerView.Adapter<BlockUserAdapter.ViewHolder> {

    List<UserEntity> blockUserList;
    Context mContext;

    OnUserCLickListener listener;

    public BlockUserAdapter(Context context,List<UserEntity> blockUserList,OnUserCLickListener listener) {
        this.mContext=context;
        this.blockUserList = blockUserList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserEntity user=blockUserList.get(position);
        holder.tv_userName.setText(user.getName());
        holder.checkBox.setChecked(false);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                user.setBlock(false);
            } else {
                user.setBlock(true);
            }
            listener.onCheckBoxClickListener();

        });
    }

    @Override
    public int getItemCount() {
        return blockUserList.size();
    }

    public void updateUserList(List<UserEntity> blockUserList){
        this.blockUserList=blockUserList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_userName;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
    public List<UserEntity> getCurrentList(){
        return this.blockUserList;
    }

    public interface OnUserCLickListener{

        void onCheckBoxClickListener();
    }
}
