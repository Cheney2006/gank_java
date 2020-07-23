package com.cheney.gankjava.ui.category;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.repository.GankRepository;

import io.reactivex.disposables.CompositeDisposable;

public class ArticleDataSourceFactory extends DataSource.Factory<Integer, Gank> {

    private MutableLiveData<ArticleDataSource> dataSourceMutableLiveData;

    private final GankRepository repository;

    private final CategoryType categoryType;

    private CompositeDisposable compositeDisposable;

    public ArticleDataSourceFactory(GankRepository repository, CategoryType categoryType, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.categoryType = categoryType;
        this.compositeDisposable = compositeDisposable;
        dataSourceMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArticleDataSource> getDataSourceMutableLiveData() {
        return dataSourceMutableLiveData;
    }



    @NonNull
    @Override
    public DataSource<Integer, Gank> create() {
        ArticleDataSource dataSource = new ArticleDataSource(repository, categoryType, compositeDisposable);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public void refresh() {
        getDataSourceMutableLiveData().getValue().invalidate();
    }
}
