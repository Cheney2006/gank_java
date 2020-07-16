package com.cheney.gankjava.ui.category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.constants.Constants;
import com.cheney.gankjava.repository.GankRepository;

import java.util.List;

import javax.inject.Inject;

public class CategoryViewModel extends ViewModel {


    private final GankRepository repository;

    public MutableLiveData<List<CategoryType>> categoryTypes= new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    @Inject
    public CategoryViewModel(GankRepository repository) {
        this.repository = repository;
        getCategoryTypes();
    }

    private void getCategoryTypes() {
        repository.getCategoryTypes(Constants.Api.CATEGORY_Article).subscribe(data -> {
            categoryTypes.postValue(data);
        }, throwable -> {
            error.postValue(throwable);
        });
    }

}