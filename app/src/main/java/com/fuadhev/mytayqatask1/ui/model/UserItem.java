package com.fuadhev.mytayqatask1.ui.model;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuadhev.mytayqatask1.R;
import com.fuadhev.mytayqatask1.data.entity.UserEntity;
import com.fuadhev.mytayqatask1.data.network.model.User;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;
import eu.davidea.flexibleadapter.items.ISectionable;
import eu.davidea.viewholders.FlexibleViewHolder;

public class UserItem extends AbstractFlexibleItem<UserItem.ChildViewHolder>  {




    private UserEntity mUser;

    public UserItem(UserEntity mUser){
        this.mUser=mUser;
    }

    public UserEntity getUserItem(){
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
        return new ChildViewHolder(view,adapter,false);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ChildViewHolder holder, int position, List<Object> payloads) {
        holder.getTv_userName().setText(getUserItem().getName());
    }



    static final class ChildViewHolder extends FlexibleViewHolder {

        private TextView tv_userName;
        private ImageView ic_block;

        public TextView getTv_userName() {
            return tv_userName;
        }

        public void setTv_userName(TextView tv_userName) {
            this.tv_userName = tv_userName;
        }

        public ImageView getIc_block() {
            return ic_block;
        }

        public void setIc_block(ImageView ic_block) {
            this.ic_block = ic_block;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        private CheckBox checkBox;

        public ChildViewHolder(View view, FlexibleAdapter adapter, boolean stickyHeader) {
            super(view, adapter, false);

            tv_userName=view.findViewById(R.id.tv_userName);
            ic_block=view.findViewById(R.id.ic_block);
            checkBox=view.findViewById(R.id.checkBox);


        }
    }
}
