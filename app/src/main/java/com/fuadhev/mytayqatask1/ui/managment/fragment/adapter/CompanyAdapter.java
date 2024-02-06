package com.fuadhev.mytayqatask1.ui.managment.fragment.adapter;

import androidx.annotation.Nullable;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractExpandableHeaderItem;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;

public class CompanyAdapter extends FlexibleAdapter<AbstractFlexibleItem>  {

    public CompanyAdapter(@Nullable List<AbstractFlexibleItem> items) {
        super(items);
    }
}
