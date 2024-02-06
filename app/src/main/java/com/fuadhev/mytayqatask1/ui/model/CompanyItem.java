package com.fuadhev.mytayqatask1.ui.model;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuadhev.mytayqatask1.R;
import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractExpandableHeaderItem;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IExpandable;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;
import eu.davidea.viewholders.ExpandableViewHolder;

public class CompanyItem extends AbstractFlexibleItem<CompanyItem.GroupViewHolder>
implements IExpandable<CompanyItem.GroupViewHolder,UserItem> ,IHeader<CompanyItem.GroupViewHolder> {


    private CompanyEntity mCompany;
    private boolean mExpanded = false;
    private List<UserItem> subItems;

    private OnCompanyItemClickListener listener;

    public CompanyItem(CompanyEntity catalog, OnCompanyItemClickListener listener) {
        mCompany = catalog;
        this.listener = listener;
    }

    public void setSubItems(List<UserItem> subItems) {
        this.subItems = subItems;
    }

    public CompanyEntity getCompanyItem() {
        return mCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyItem that = (CompanyItem) o;
        return mCompany.getName().equals(that.getCompanyItem().getName());
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_company;
    }

    @Override
    public GroupViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new GroupViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, GroupViewHolder holder, int position, List<Object> payloads) {
        holder.getTv_companyName().setText(mCompany.getName());
        holder.getIc_more().setImageResource(R.drawable.ic_more);
        holder.ic_more.setOnClickListener(view -> listener.onCompanyItemClicked(holder.getAdapterPosition()));
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        Log.d("Fuad06", "setExpanded: " + expanded);
        Log.d("Fuad06", "setExpanded: " + mExpanded);
        this.mExpanded = expanded;
    }

    @Override
    public int getExpansionLevel() {
        return 0;
    }

    @Override
    public List<UserItem> getSubItems() {
        return subItems;
    }


    static final class GroupViewHolder extends ExpandableViewHolder {

        private TextView tv_companyName;
        private ImageView ic_more;
        public GroupViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter,false);


            tv_companyName=view.findViewById(R.id.tv_companyName);
            ic_more=view.findViewById(R.id.ic_more);
//            /*ic_more.setOnClickListener(v -> */adapter.mItemClickListener.onItemClick(view, getFlexibleAdapterPosition());

        }

        public ImageView getIc_more() {
            return ic_more;
        }

        public TextView getTv_companyName() {
            return tv_companyName;
        }


//        @Override
//        protected boolean isViewExpandableOnClick() {
//            return true;
//        }
    }

    public interface OnCompanyItemClickListener{
        void onCompanyItemClicked(int position);
    }
}
