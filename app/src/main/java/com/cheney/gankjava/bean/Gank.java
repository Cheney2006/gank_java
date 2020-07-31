package com.cheney.gankjava.bean;


import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;


public class Gank {


    @SerializedName("_id")
    private String gankId;

    private String author;
    private String category;
    private String content;
    private String createdAt;
    private String desc;
    private String email;
    private int index;
    private boolean isOriginal;
    private String license;
    private int likeCounts;
    private String markdown;
    private String originalAuthor;
    private String publishedAt;
    private int stars;
    private int status;
    private String title;
    private String type;
    private String updatedAt;
    private String url;
    private int views;
    private String[] images;
    private String[] likes;
    private String[] tags;


    /**
     * 返回对应的图
     */
    public String getImageUrl(int index) {
        return (images != null && images.length > index) ? images[index] : "";
    }


    public String getGankId() {
        return gankId;
    }

    public void setGankId(String gankId) {
        this.gankId = gankId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gank gank = (Gank) o;
        return Objects.equals(gankId, gank.gankId) &&
                Objects.equals(author, gank.author) &&
                Objects.equals(category, gank.category) &&
                Objects.equals(desc, gank.desc) &&
                Objects.equals(publishedAt, gank.publishedAt) &&
                Objects.equals(title, gank.title) &&
                Objects.equals(type, gank.type) &&
                Objects.equals(url, gank.url) &&
                Arrays.equals(images, gank.images);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(gankId, author, category, desc, publishedAt, title, type, url);
        result = 31 * result + Arrays.hashCode(images);
        return result;
    }
}
