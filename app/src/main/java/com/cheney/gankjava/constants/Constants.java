package com.cheney.gankjava.constants;

public class Constants {


    public final static class Api {
        /**
         * 默认url
         */
        public static final String BASE_URL = "https://gank.io/api/v2/";

        /**
         * hotType可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）
         */
        public static final String HOT_VIEWS = "views";
        public static final String HOT_LIKES = "likes";
        public static final String HOT_COMMENTS = "comments";


        /**
         * 全部
         */
        public static final String CATEGORY_ALL = "All";
        /**
         * 干货
         */
        public static String CATEGORY_GAN_HUO = "GanHuo";
        /**
         * 妹纸
         */
        public static String CATEGORY_Girl = "Girl";
        /**
         * 专题分享
         */
        public static String CATEGORY_Article = "Article";




    }


    public final static class NamedKey {
        public static final String RETROFIT_GITHUB = "githubRetrofi";
        public static final String RETROFIT_GANK = "gankRetrofi";
        public static final String PACKAGE_NAME = "packageName";
        public static final String VERSION_NAME = "versionName";
        public static final String VERSION_CODE = "versionCode";
    }
}
