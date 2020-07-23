package com.cheney.gankjava.ui.girl;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;

import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.constants.Constants;
import com.cheney.gankjava.repository.GankRepository;

import io.reactivex.disposables.CompositeDisposable;

public class GirlDataSource extends ItemKeyedDataSource<Integer, Gank> {


    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> canLoadMore = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();
    private final GankRepository repository;
    private final CompositeDisposable compositeDisposable;
    private int pageNo = 1;

    public GirlDataSource(GankRepository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Gank> callback) {
        isLoading.postValue(true);
        repository.getByCategoryType(Constants.Api.CATEGORY_Girl, Constants.Api.CATEGORY_Girl, params.requestedLoadSize, pageNo)
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(result -> {
                    isLoading.postValue(false);
                    if (result.getPage_count() > result.getPage()) {
                        pageNo++;
                        canLoadMore.postValue(true);
                    } else {
                        canLoadMore.postValue(false);
                    }
                    callback.onResult(result.getData());
                }, throwable -> {
                    isLoading.postValue(false);
                    error.postValue(throwable);
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Gank> callback) {
        if (canLoadMore.getValue() == null || !canLoadMore.getValue()) {
            return;
        }
        repository.getByCategoryType(Constants.Api.CATEGORY_Girl, Constants.Api.CATEGORY_Girl, params.requestedLoadSize, params.key)
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(result -> {
                    if (result.getPage_count() > result.getPage()) {
                        pageNo++;
                        canLoadMore.postValue(true);
                    } else {
                        canLoadMore.postValue(false);
                    }
                    callback.onResult(result.getData());
                }, throwable -> error.postValue(throwable));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Gank> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Gank item) {
        return pageNo;
    }


    public void onCleared() {
        compositeDisposable.clear();
    }
}
