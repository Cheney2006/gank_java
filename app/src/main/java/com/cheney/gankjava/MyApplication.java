package com.cheney.gankjava;


import com.cheney.gankjava.base.di.DaggerAppComponent;
import com.cheney.gankjava.base.di.NetworkModule;
import com.cheney.gankjava.constants.Constants;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.plugins.RxJavaPlugins;

public class MyApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setRxJavaErrorHandler();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(new NetworkModule(Constants.Api.BASE_URL), this);
    }

    /**
     * RxJava2 取消订阅后，抛出的异常无法捕获，导致程序崩溃（io.reactivex.exceptions.UndeliverableException:The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with.）
     */
    private void setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            throwable.printStackTrace();
        });
    }

}
