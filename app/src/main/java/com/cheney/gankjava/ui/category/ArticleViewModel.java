package com.cheney.gankjava.ui.category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.constants.Constants;
import com.cheney.gankjava.repository.GankRepository;

import java.util.List;

import javax.inject.Inject;

public class ArticleViewModel extends ViewModel {

    private final GankRepository repository;

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<CategoryType> categoryType = new MutableLiveData<>();
    public MutableLiveData<List<Gank>> ganks = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    @Inject
    public ArticleViewModel(GankRepository repository) {
        this.repository = repository;
    }

    public void initType(CategoryType type) {
        categoryType.setValue(type);
        getByCategoryType();
    }

    private void getByCategoryType() {
        isLoading.postValue(true);
        repository.getByCategoryType(Constants.Api.CATEGORY_Article, categoryType.getValue().getType()).subscribe(data -> {
            isLoading.postValue(false);
            ganks.postValue(data);
        }, throwable -> {
            isLoading.postValue(false);
            error.postValue(throwable);
        });
    }

    public void refresh() {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            getByCategoryType();
        }
    }
}