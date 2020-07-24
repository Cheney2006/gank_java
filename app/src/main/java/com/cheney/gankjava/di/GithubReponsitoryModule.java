package com.cheney.gankjava.di;

import com.cheney.gankjava.api.GitHubApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GithubReponsitoryModule {

    public static final String BASE_API = "https://api.github.com";

    @Singleton
    @Provides
    @Github
    Retrofit provideGitHubRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    GitHubApi provideGitHubApi(@Github Retrofit gitHubRetrofit) {
        return gitHubRetrofit.create(GitHubApi.class);
    }
}
