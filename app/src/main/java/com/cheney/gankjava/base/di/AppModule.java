package com.cheney.gankjava.base.di;

import android.app.Application;
import android.content.Context;

import com.cheney.gankjava.di.GithubReponsitoryModule;
import com.cheney.gankjava.di.RepositoryModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(includes = {RepositoryModule.class, CommonModule.class, GithubReponsitoryModule.class})
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Context provideContext(Application application);

}
