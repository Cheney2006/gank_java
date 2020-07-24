package com.cheney.gankjava.repository;

import com.cheney.gankjava.api.GitHubApi;
import com.cheney.gankjava.bean.Authorization;

import java.net.URLEncoder;
import java.util.Base64;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GitHubRepository {


    private final GitHubApi apiService;

    @Inject
    GitHubRepository(GitHubApi apiService) {
        this.apiService = apiService;
    }

    public Single<Authorization.Response> getAccessToken(String userName, String password) {
        // String basic = "Basic ${base64Encode(utf8.encode("$userName:$password"))}";
        String basic = "Basic ";
        Authorization.Request request = new Authorization.Request();
        request.setClient_id("a1024445dd418375e71b");
        request.setClient_secret("108c59af90318a6b70c0cb2f8031b6154aee0949");
        request.setNote("com.cheney.gankjava");
        request.setNoteUrl("http://localhost:8080/callback");
        request.setScopes(new String[]{"public_repo"});
        return apiService.getAccessToken(basic, request).subscribeOn(Schedulers.io());
    }

}
