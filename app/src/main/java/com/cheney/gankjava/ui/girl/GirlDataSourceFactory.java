package com.cheney.gankjava.ui.girl;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.cheney.gankjava.bean.Gank;

import javax.inject.Inject;

public class GirlDataSourceFactory extends DataSource.Factory<Integer, Gank> {


    public MutableLiveData<GirlDataSource> dataSourceMutableLiveData=new MutableLiveData<>();

    private GirlDataSource dataSource;

    @Inject
    public GirlDataSourceFactory(GirlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public DataSource<Integer, Gank> create() {
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public void refresh(){
        dataSourceMutableLiveData.getValue().invalidate();
    }
}
