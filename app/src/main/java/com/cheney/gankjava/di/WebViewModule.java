package com.cheney.gankjava.di;

import com.cheney.gankjava.ui.web.WebViewFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WebViewModule {

    @ContributesAndroidInjector
    abstract WebViewFragment provideWebViewFragment();
}
