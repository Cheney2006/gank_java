package com.cheney.gankjava.ui.girl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.repository.GankRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class GirlViewModel extends ViewModel {

    /**
     * 注入方式，更倾向构造传参方式
     */
    private GirlDataSourceFactory factory;

    private CompositeDisposable disposable;

    public LiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<Throwable> error = new MutableLiveData<>();
    public LiveData<PagedList<Gank>> pagedListLiveData;

    @Inject
    public GirlViewModel(GankRepository repository) {
        disposable = new CompositeDisposable();
        this.factory = new GirlDataSourceFactory(repository, disposable);
        initPaging();
    }

    private void initPaging() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .setPrefetchDistance(2)
                .build();
        pagedListLiveData = new LivePagedListBuilder<>(factory, config).build();

        isLoading = Transformations.switchMap(factory.dataSourceMutableLiveData, input -> input.isLoading);

        error = Transformations.switchMap(factory.dataSourceMutableLiveData, input -> input.error);

    }

    public void refresh() {
        factory.refresh();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        factory.dataSourceMutableLiveData.getValue().onCleared();
    }
}