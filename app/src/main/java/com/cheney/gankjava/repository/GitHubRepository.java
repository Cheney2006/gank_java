package com.cheney.gankjava.repository;

import android.util.Base64;

import com.cheney.gankjava.api.GitHubApi;
import com.cheney.gankjava.bean.Authorization;
import com.cheney.gankjava.util.Base64Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
//        String basic = null;
//        try {
//            basic = "Basic " + Base64.encodeToString(URLEncoder.encode(userName + ":" + password,"utf-8").getBytes(),Base64.NO_WRAP);
//            basic = "Basic " + Base64Util.encode(URLEncoder.encode(userName + ":" + password,"utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Authorization.Request request = new Authorization.Request();
//        request.setClient_id("a1024445dd418375e71b");
//        request.setClient_secret("108c59af90318a6b70c0cb2f8031b6154aee0949");
//        request.setNote("com.cheney.gankjava");
//        request.setNoteUrl("http://localhost:8080/callback");
//        request.setScopes(new String[]{"public_repo"});
        //token 0a78c1c9557e5373b4ce329631d8ea5767e5463a
        return apiService.getAccessToken("", request).subscribeOn(Schedulers.io());
    }

}
