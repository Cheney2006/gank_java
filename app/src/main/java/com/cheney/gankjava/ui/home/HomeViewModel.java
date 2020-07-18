package com.cheney.gankjava.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.HomeItem;
import com.cheney.gankjava.repository.GankRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomeViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;

    private final GankRepository gankRepository;

    @Inject
    public HomeViewModel(GankRepository gankRepository) {
        this.gankRepository = gankRepository;
        compositeDisposable = new CompositeDisposable();
        init();
    }

    //是否加载
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<List<HomeItem>> hot = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    private void init() {
        isLoading.postValue(true);
        gankRepository.getHomeGanks()
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(data -> {
                    isLoading.postValue(false);
                    hot.postValue(data);
                }, throwable -> {
                    isLoading.postValue(false);
                    error.postValue(throwable);
                });
    }

    public void refresh() {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            init();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}