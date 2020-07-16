package com.cheney.gankjava.api;

import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.GankBanner;
import com.cheney.gankjava.bean.Result;
import com.cheney.gankjava.bean.Comment;
import com.cheney.gankjava.bean.Gank;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 获取某一个分类下的数据, 可以返回Observable, Flowable, Maybe, 建议返回Single
 * 网络请求是一个Request对应一个 Response，不会出现背压情况，所以不考虑 Flowable；网络请求是一个Request对应一个 Response，不是一个连续的事件流，所以在 onNext 被调用之后，onComplete 就会被马上调用，所以只需要 onNext 和 onComplete 其中一个就够了，不考虑 Observable、Maybe ；
 */
public interface GankApi {


    /**
     * 首页banner轮播
     *
     * @return
     */
    @GET("banners")
    Single<Result<List<GankBanner>>> getBanners();


    /**
     * 本周最热 API
     *
     * @param hotType  可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）
     * @param category 可接受参数 Article | GanHuo | Girl
     * @param count    [1, 20]
     * @return
     */
    @GET("hot/{hotType}/category/{category}/count/{count}")
    Single<Result<List<Gank>>> getHot(@Path("hotType") String hotType, @Path("category") String category, @Path("count") int count);


    /**
     * 获取所有分类具体子分类[types]数据
     *
     * @param category 可接受参数 Article | GanHuo | Girl Article: 专题分类、 GanHuo: 干货分类 、 Girl:妹子图
     */
    @GET("categories/{category_type}")
    Single<Result<List<CategoryType>>> getCategoryTypes(@Path("category_type") String category);


    /**
     * 分类数据 API
     *
     * @param category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * @param type     可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * @param count    [10, 50]
     * @param page     >=1
     */
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    Single<Result<List<Gank>>> getByCategoryType(
            @Path("category") String category,
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page);


    /**
     * 文章详情 API
     *
     * @param id post_id 可接受参数 文章id[分类数据API返回的_id字段]
     * @return
     */
    @GET("post/{id}")
    Single<Result<Gank>> getById(@Path("id") String id);


    /**
     * 文章评论获取 API post_id 可接受参数 文章Id
     *
     * @param id
     * @return
     */
    @GET("post/comments/{postId}")
    Single<Result<Comment>> getComments(@Path("postId") String id);

    /**
     * 搜索 API
     *
     * @param search   search 可接受参数 要搜索的内容
     * @param category category 可接受参数 All[所有分类] | Article | GanHuo
     * @param type     可接受参数 Android | iOS | Flutter ...，即分类API返回的类型数据
     * @param page     page: >=1
     * @param count    [10, 50]
     * @return
     */
    @GET("search/{search}/category/{category}/type/{type}/page/{page}/count/{count}")
    Single<Result<List<Gank>>> searchGanks(@Path("search") String search, @Path("category") String category,
                                           @Path("type") String type,
                                           @Path("page") int page,
                                           @Path("count") int count);
}
