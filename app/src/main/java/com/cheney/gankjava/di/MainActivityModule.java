package com.cheney.gankjava.di;

import com.cheney.gankjava.ui.category.ArticleFragment;
import com.cheney.gankjava.ui.category.CategoryFragment;
import com.cheney.gankjava.ui.home.HomeFragment;
import com.cheney.gankjava.ui.my.MyFragment;
import com.cheney.gankjava.ui.sister.SisterFragment;

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
    abstract SisterFragment provideSisterFragment();

    @ContributesAndroidInjector
    abstract MyFragment provideMyFragment();
}
