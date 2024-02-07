package com.fuadhev.mytayqatask1.data.local;

import com.fuadhev.mytayqatask1.data.entity.CompanyEntity;
import com.fuadhev.mytayqatask1.data.local.db.ObjectBox;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;
import io.objectbox.query.QueryBuilder;

public class CompanyDao {
    private static final Box<CompanyEntity> boxStore = ObjectBox.getBoxStore().boxFor(CompanyEntity.class);


    public static void insertAll(CompanyEntity companyEntity) {
        boxStore.put(companyEntity);
    }

    public static List<CompanyEntity> getAllCompany() {
        return boxStore.getAll();
    }

    public static ObjectBoxLiveData<CompanyEntity> checkLocalData() {
        QueryBuilder<CompanyEntity> queryBuilder = boxStore.query();
        return new ObjectBoxLiveData<>(queryBuilder.build());
    }

    public static void attachCompany(CompanyEntity companyEntity) {
        boxStore.attach(companyEntity);
    }


}
