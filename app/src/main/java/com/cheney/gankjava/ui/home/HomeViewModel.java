package com.cheney.gankjava.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.bean.HomeItem;
import com.cheney.gankjava.repository.GankRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class HomeViewModel extends ViewModel {

    private CompositeDisposable disposable;

    private final GankRepository gankRepository;

    @Inject
    public HomeViewModel(GankRepository gankRepository) {
        this.gankRepository = gankRepository;
        disposable = new CompositeDisposable();
        init();
    }

    //是否加载
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<List<HomeItem>> hot = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    private void init() {
        isLoading.postValue(true);
        disposable.add(gankRepository.getHomeGanks().subscribe(data -> {
            isLoading.postValue(false);
            hot.postValue(data);
        }, throwable -> {
            isLoading.postValue(false);
            error.postValue(throwable);
        }));
    }

    public void refresh() {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            init();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}