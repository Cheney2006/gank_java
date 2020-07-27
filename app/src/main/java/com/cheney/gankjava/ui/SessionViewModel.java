package com.cheney.gankjava.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.ProgressBean;
import com.cheney.gankjava.bean.User;

import javax.inject.Inject;

public class SessionViewModel extends ViewModel {

    public MutableLiveData<ProgressBean> progressLiveData = new MutableLiveData();

    public MutableLiveData<User> userInfo = new MutableLiveData();

    @Inject
    public SessionViewModel() {
    }
}
