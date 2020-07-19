package com.cheney.gankjava.repository;

import com.cheney.gankjava.api.GankApi;
import com.cheney.gankjava.bean.CategoryType;
import com.cheney.gankjava.bean.Gank;
import com.cheney.gankjava.bean.GankBanner;
import com.cheney.gankjava.bean.HomeItem;
import com.cheney.gankjava.constants.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GankRepository {
    /**
     * 生成API 实例
     */
    private final GankApi apiService;

    @Inject
    GankRepository(GankApi apiService) {
        this.apiService = apiService;
    }


    /**
     * 取得banner
     *
     * @return
     */
    public Single<List<GankBanner>> getBanner() {
        return apiService.getBanners().map(result -> {
            if (result.isOk()) {
                return result.getData();
            } else {
                throw new IOException("Get Error From Server");
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 获取推荐
     *
     * @return
     */
    public Single<List<Gank>> getHotGanks() {
        return apiService.getHot(Constants.Api.HOT_VIEWS, Constants.Api.CATEGORY_Article, 20).map(result -> {
            if (result.isOk()) {
                return result.getData();
            } else {
                throw new IOException("Get Error From Server");
            }
        }).subscribeOn(Schedulers.io());
    }



    /**
     * 获取所有分类具体子分类[types]数据
     *
     * @param category 可接受参数 Article | GanHuo | Girl Article: 专题分类、 GanHuo: 干货分类 、 Girl:妹子图
     */
    public Single<List<CategoryType>> getCategoryTypes(String category) {
        return apiService.getCategoryTypes(category).map(result -> {
            if (result.isOk()) {
                return result.getData();
            } else {
                throw new IOException("Get Error From Server");
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 分类数据 API
     *
     * @param category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * @param type     可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * @param count    [10, 50]
     * @param page     >=1
     * @return
     */
    public Single<List<Gank>> getByCategoryType(String category, String type, int count, int page) {
        return apiService.getByCategoryType(category, type, count, page).map(result -> {
            if (result.isOk()) {
                return result.getData();
            } else {
                throw new IOException("Get Error From Server");
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 取得详情
     *
     * @param id 文章id
     * @return
     */
    public Single<Gank> getGankById(String id) {
        return apiService.getById(id).map(result -> {
            if (result.isOk()) {
                return result.getData();
            } else {
                throw new IOException("Get Error From Server");
            }
        }).subscribeOn(Schedulers.io());
    }

}
