package com.cheney.gankjava.di;

import com.cheney.gankjava.api.GankApi;
import com.cheney.gankjava.base.di.NetworkModule;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class RepositoryModule {

    @Singleton
    @Provides
    GankApi provideGankApi(@Gank Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }
}
