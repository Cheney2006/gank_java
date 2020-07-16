package com.cheney.gankjava;

import com.cheney.gankjava.base.di.DaggerAppComponent;
import com.cheney.gankjava.base.di.NetworkModule;
import com.cheney.gankjava.constants.Constants;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MyApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(new NetworkModule(Constants.Api.BASE_URL), this);
    }


}
