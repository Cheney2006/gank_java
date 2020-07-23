package com.cheney.gankjava.ui.girl;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.repository.GankRepository;

import io.reactivex.disposables.CompositeDisposable;


public class GirlDataSourceFactory extends DataSource.Factory<Integer, Gank> {


    public MutableLiveData<GirlDataSource> dataSourceMutableLiveData = new MutableLiveData<>();

    private final GankRepository repository;
    private final CompositeDisposable compositeDisposable;

    public GirlDataSourceFactory(GankRepository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
    }

    @NonNull
    @Override
    public DataSource<Integer, Gank> create() {
        GirlDataSource dataSource = new GirlDataSource(repository, compositeDisposable);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public void refresh() {
        //通过注解生成GirlDataSource，这里不生效
        dataSourceMutableLiveData.getValue().invalidate();
    }
}
