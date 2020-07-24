package com.cheney.gankjava.api;

import com.cheney.gankjava.bean.Authorization;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * github登录授权
 */
public interface GitHubApi {
    //    @Headers({
//            "Accept: application/vnd.yourapi.v1.full+json",
//            "User-Agent: Your-App-Name"
//    })
    @Headers("Cache-Control: no-cache")
    @POST("authorizations")
    Single<Authorization.Response> getAccessToken(@Header("Authorization") String basic, @Body Authorization.Request request);

    /**
     * user?access_token=xx
     *
     * @param accessToken
     * @return
     */
    @GET("user")
    Single<Authorization.Response> getUserInfo(@Query("access_token") String accessToken);
}
