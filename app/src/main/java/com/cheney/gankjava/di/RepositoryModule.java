package com.cheney.gankjava.di;

import com.cheney.gankjava.api.GankApi;
import com.cheney.gankjava.base.di.NetworkModule;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class RepositoryModule {

//    @Singleton
    @Provides
    GankApi provideGankApi(Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }

}
