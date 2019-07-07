package com.example.softd.yichun201907.DB;

import org.litepal.crud.LitePalSupport;

public class Entity extends LitePalSupport {
    //标识
    String idid = "";
    //标题
    String title = "";
    //图片地址
    int imgUrl;
    //文章内容
    String content = "";
    //是否被收藏
    int isCollection;

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public String getIdid() {
        return idid;
    }

    public void setIdid(String idid) {
        this.idid = idid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
