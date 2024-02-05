package com.fuadhev.mytayqatask1.ui.model;

import android.view.View;

import com.fuadhev.mytayqatask1.R;
import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractExpandableHeaderItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.ExpandableViewHolder;

public class CompanyItem extends AbstractExpandableHeaderItem<CompanyItem.GroupViewHolder,UserItem> {


    private CompanyEntity companyEntity;

    public CompanyItem(CompanyEntity catalog) {
        super();
        setExpanded(false);
        companyEntity = catalog;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_company;
    }

    @Override
    public GroupViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new GroupViewHolder(view,adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, GroupViewHolder holder, int position, List<Object> payloads) {

    }


    static final class GroupViewHolder extends ExpandableViewHolder{




        public GroupViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
        }


        public GroupViewHolder(View view, FlexibleAdapter adapter, boolean stickyHeader) {
            super(view, adapter, stickyHeader);
        }
    }
}
