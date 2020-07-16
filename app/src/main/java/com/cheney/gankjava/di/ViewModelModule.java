package com.cheney.gankjava.di;

import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.base.di.ViewModelKey;
import com.cheney.gankjava.ui.category.ArticleViewModel;
import com.cheney.gankjava.ui.category.CategoryViewModel;
import com.cheney.gankjava.ui.home.HomeViewModel;
import com.cheney.gankjava.ui.my.MyViewModel;
import com.cheney.gankjava.ui.sister.SisterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(CategoryViewModel categoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel.class)
    abstract ViewModel bindArticleViewModel(ArticleViewModel articleViewModel);

//
//    @Binds
//    @IntoMap
//    @ViewModelKey(SisterViewModel.class)
//    abstract ViewModel bindSisterViewModel(SisterViewModel sisterViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MyViewModel.class)
//    abstract ViewModel bindMyViewModel(MyViewModel myViewModel);
}
