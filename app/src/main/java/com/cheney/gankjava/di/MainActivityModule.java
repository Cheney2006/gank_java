package com.cheney.gankjava.di;

import com.cheney.gankjava.ui.category.ArticleFragment;
import com.cheney.gankjava.ui.category.CategoryFragment;
import com.cheney.gankjava.ui.home.HomeFragment;
import com.cheney.gankjava.ui.my.MyFragment;
import com.cheney.gankjava.ui.girl.GirlFragment;
import com.cheney.gankjava.ui.my.SettingsFragment;
import com.cheney.gankjava.ui.web.WebViewFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();

    @ContributesAndroidInjector
    abstract CategoryFragment provideCategoryFragment();

    @ContributesAndroidInjector
    abstract ArticleFragment provideArticleFragment();

    @ContributesAndroidInjector
    abstract GirlFragment provideSisterFragment();

    @ContributesAndroidInjector
    abstract MyFragment provideMyFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment provideSettingsFragment();

}
