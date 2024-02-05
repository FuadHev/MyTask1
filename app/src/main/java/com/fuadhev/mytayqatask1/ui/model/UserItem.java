package com.fuadhev.mytayqatask1.ui.model;

import android.view.View;

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

public class UserItem extends AbstractFlexibleItem<UserItem.ChildViewHolder> implements ISectionable<UserItem.ChildViewHolder, IHeader> {



    private IHeader iHeader;
    private UserEntity mUser;


    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_user;
    }

    @Override
    public ChildViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return null;
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ChildViewHolder holder, int position, List<Object> payloads) {

    }

    @Override
    public IHeader getHeader() {
        return null;
    }

    @Override
    public void setHeader(IHeader header) {

    }

    static final class ChildViewHolder extends FlexibleViewHolder {


        public ChildViewHolder(View view, FlexibleAdapter adapter, boolean stickyHeader) {
            super(view, adapter, true);
        }
    }
}
