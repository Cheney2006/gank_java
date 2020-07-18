package com.cheney.gankjava.ui.girl;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;

import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.constants.Constants;
import com.cheney.gankjava.repository.GankRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GirlDataSource extends ItemKeyedDataSource<Integer, Gank> {

    private int pageNo = 1;
    private final GankRepository repository;

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    public GirlDataSource(GankRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Gank> callback) {
        isLoading.postValue(true);
        repository.getByCategoryType(Constants.Api.CATEGORY_Girl, Constants.Api.CATEGORY_Girl, params.requestedLoadSize, pageNo)
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(ganks -> {
                    isLoading.postValue(false);
                    pageNo++;
                    callback.onResult(ganks);
                }, throwable -> {
                    isLoading.postValue(false);
                    error.postValue(throwable);
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Gank> callback) {
        repository.getByCategoryType(Constants.Api.CATEGORY_Girl, Constants.Api.CATEGORY_Girl, params.requestedLoadSize, params.key).doOnSubscribe(disposable -> compositeDisposable.add(disposable)).subscribe(ganks -> {
            pageNo++;
            callback.onResult(ganks);
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


    public void onCleared(){
        compositeDisposable.clear();
    }
}
