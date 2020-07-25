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
//    @Headers("Cache-Control: no-cache")
    @Headers({
            "User-Agent:Mozilla/5.0",
            "Authorization:token 0a78c1c9557e5373b4ce329631d8ea5767e5463a",
            "Content-Type:application/json",
            "method:GET",
            "Accept:application/json"
    })
    @POST("authorizations")
    Single<Authorization.Response> getAccessToken(@Header("Authorization") String basic, @Body Authorization.Request request);

    /**
     * user?access_token=xx
     *
     * @param accessToken
     * @return
     */
    @Headers({
            "User-Agent:Mozilla/5.0",
            "Authorization:token 0a78c1c9557e5373b4ce329631d8ea5767e5463a",
            "Content-Type:application/json",
            "method:GET",
            "Accept:application/json"
    })
    @GET("user")
    Single<Authorization.Response> getUserInfo(@Query("access_token") String accessToken);
}
