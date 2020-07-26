package com.cheney.gankjava.di;

import androidx.lifecycle.ViewModel;

import com.cheney.gankjava.base.di.ViewModelKey;
import com.cheney.gankjava.ui.SessionViewModel;
import com.cheney.gankjava.ui.category.ArticleViewModel;
import com.cheney.gankjava.ui.category.CategoryViewModel;
import com.cheney.gankjava.ui.home.HomeViewModel;
import com.cheney.gankjava.ui.girl.GirlViewModel;
import com.cheney.gankjava.ui.login.LoginViewModel;
import com.cheney.gankjava.ui.my.MyViewModel;
import com.cheney.gankjava.ui.web.WebViewViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SessionViewModel.class)
    abstract ViewModel bindSessionViewModel(SessionViewModel sessionViewModel);

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


    @Binds
    @IntoMap
    @ViewModelKey(GirlViewModel.class)
    abstract ViewModel bindGirlViewModel(GirlViewModel girlViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel.class)
    abstract ViewModel bindMyViewModel(MyViewModel myViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WebViewViewModel.class)
    abstract ViewModel bindWebViewViewModel(WebViewViewModel webViewViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel webViewViewModel);
}
