package com.fuadhev.mytayqatask1.ui.model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuadhev.mytayqatask1.R;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class UserItem extends AbstractFlexibleItem<UserItem.ChildViewHolder> {


    private UserEntity mUser;

    private UpdateIsBlockMenuVisibility listener;

    public UserItem(UserEntity mUser, UpdateIsBlockMenuVisibility onUserItemClickListener) {
        this.mUser = mUser;
        this.listener = onUserItemClickListener;
    }

    public UserEntity getUserItem() {
        return mUser;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UserItem && mUser != null) {
            UserItem cgi = (UserItem) o;
            return mUser.getId() == cgi.getUserItem().getId();
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_user;
    }

    @Override
    public ChildViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ChildViewHolder(view, adapter, false);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ChildViewHolder holder, int position, List<Object> payloads) {
        holder.tv_userName.setText(getUserItem().getName());
        holder.ic_block.setVisibility(View.GONE);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                mUser.setBlock(true);
            } else {
                mUser.setBlock(false);
            }
            listener.setBlockMenuVisibility();

        });
    }



    static final class ChildViewHolder extends FlexibleViewHolder {

        private TextView tv_userName;
        private ImageView ic_block;
        private CheckBox checkBox;

        public ChildViewHolder(View view, FlexibleAdapter adapter, boolean stickyHeader) {
            super(view, adapter, false);

            tv_userName = view.findViewById(R.id.tv_userName);
            ic_block = view.findViewById(R.id.ic_block);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

    public interface UpdateIsBlockMenuVisibility {
        void setBlockMenuVisibility();
    }
}
