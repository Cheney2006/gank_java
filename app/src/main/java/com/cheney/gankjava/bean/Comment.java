package com.cheney.gankjava.bean;

public class Comment {

    /**
     * _id : 5ecb7f06808d6d2fe6b56f0c
     * userId : 5ecb7dadee6ba981da2af392
     * postId : 5e5279c441bd447e3b978073
     * userName : 其乐
     * comment : 老哥 我也想学
     * headUrl : https://ae01.alicdn.com/kf/Ue83b41e38853417db2c25e43a630aa39W.jpg
     * ups : 0
     * createdAt : 2020-05-25 16:17:10
     * secondParentId : 5ecb7f06808d6d2fe6b56f0b
     * secondParentName : null
     */

    private String _id;
    private String userId;
    private String postId;
    private String userName;
    private String comment;
    private String headUrl;
    private int ups;
    private String createdAt;
    private String secondParentId;
    private Object secondParentName;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSecondParentId() {
        return secondParentId;
    }

    public void setSecondParentId(String secondParentId) {
        this.secondParentId = secondParentId;
    }

    public Object getSecondParentName() {
        return secondParentName;
    }

    public void setSecondParentName(Object secondParentName) {
        this.secondParentName = secondParentName;
    }
}
