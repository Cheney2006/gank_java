package com.cheney.gankjava.ui.web;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class WebViewViewModel extends ViewModel {


    @Inject
    public WebViewViewModel() {
    }

    public MutableLiveData<Integer> progress = new MutableLiveData<>();


}