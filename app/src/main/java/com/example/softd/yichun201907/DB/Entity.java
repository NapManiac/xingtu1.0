package com.example.softd.yichun201907.DB;

public class Entity {
    //标识
    String idid = "";
    //标题
    String title = "";
    //图片地址
    String imgUrl = "";
    //文章内容
    String content = "";

    public String getId() {
        return idid;
    }

    public void setId(String id) {
        this.idid = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
