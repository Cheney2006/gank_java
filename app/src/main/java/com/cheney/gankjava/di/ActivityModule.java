package com.cheney.gankjava.di;

import com.cheney.gankjava.ui.MainActivity;
import com.cheney.gankjava.ui.web.WebViewActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity provideMainActivity();

    @ContributesAndroidInjector(modules = WebViewModule.class)
    abstract WebViewActivity provideWebViewActivity();
}
