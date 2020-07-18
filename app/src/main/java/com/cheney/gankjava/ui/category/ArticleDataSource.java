package com.cheney.gankjava.ui.category;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;

import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.constants.Constants;
import com.cheney.gankjava.repository.GankRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ArticleDataSource extends ItemKeyedDataSource<Integer, Gank> {

    private static final String TAG = "ArticleDataSource";

    private int pageNo = 1;

    private final GankRepository repository;

    private final CategoryType categoryType;

    private CompositeDisposable compositeDisposable;

    private MutableLiveData<Throwable> error = new MutableLiveData<>();

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    //    @Inject
    public ArticleDataSource(GankRepository repository, CategoryType categoryType, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.categoryType = categoryType;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Gank> callback) {
        Log.d(TAG, "Fetching  params requestedInitialKey:" + params.requestedInitialKey + ",requestedLoadSize=" + params.requestedLoadSize);
        isLoading.postValue(true);
        repository.getByCategoryType(Constants.Api.CATEGORY_Article, categoryType.getType(), params.requestedLoadSize, pageNo)
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(ganks -> onSuccess(callback, ganks), throwable -> {
                    error.postValue(throwable);
                    isLoading.postValue(false);
                });
    }

    private void onSuccess(@NonNull LoadInitialCallback<Gank> callback, List<Gank> ganks) {
        isLoading.postValue(false);
        pageNo++;
        callback.onResult(ganks);
    }


    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Gank> callback) {
        Log.d(TAG, "Fetching next page:" + pageNo);
        Log.d(TAG, "Fetching next page params key:" + params.key + ",requestedLoadSize=" + params.requestedLoadSize);
        repository.getByCategoryType(Constants.Api.CATEGORY_Article, categoryType.getType(), params.requestedLoadSize, params.key)
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(ganks -> {
                    pageNo++;
                    callback.onResult(ganks);
                }, this::onError);
    }

    private void onError(Throwable throwable) {
        error.postValue(throwable);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Gank> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Gank item) {
        return pageNo;
    }


    public MutableLiveData<Throwable> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

}
