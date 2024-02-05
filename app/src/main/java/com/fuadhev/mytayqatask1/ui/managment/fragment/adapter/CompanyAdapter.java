package com.fuadhev.mytayqatask1.ui.managment.fragment.adapter;

import androidx.annotation.Nullable;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractExpandableHeaderItem;
import eu.davidea.flexibleadapter.items.IFlexible;

public class CompanyAdapter extends FlexibleAdapter<IFlexible>  {

//    public CompanyAdapter(@Nullable List<IFlexible> items) {
//        super(items);
//    }
//
//    public CompanyAdapter(@Nullable List<IFlexible> items, @Nullable Object listeners) {
//        super(items, listeners);
//    }

    public CompanyAdapter(@Nullable List<IFlexible> items, @Nullable Object listeners, boolean stableIds) {
        super(items, listeners, true);
    }
}
