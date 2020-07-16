package com.cheney.gankjava.bean;

import java.util.Objects;

public class GankBanner {

    /**
     * image : http://gank.io/images/cfb4028bfead41e8b6e34057364969d1
     * title : 干货集中营新版更新
     * url : https://gank.io/migrate_progress
     */

    private String image;
    private String title;
    private String url;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GankBanner that = (GankBanner) o;
        return Objects.equals(image, that.image) &&
                Objects.equals(title, that.title) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, title, url);
    }
}
