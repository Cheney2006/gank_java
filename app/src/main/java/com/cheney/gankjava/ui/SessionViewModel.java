package com.cheney.gankjava.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.bean.Progress;
import com.cheney.gankjava.bean.User;

import javax.inject.Inject;

public class SessionViewModel extends ViewModel {

    public MutableLiveData<Progress> progressMutableLiveData=new MutableLiveData<Progress>();

    public MutableLiveData<User> userInfo=new MutableLiveData<User>();

    @Inject
    public SessionViewModel() {
    }
}
