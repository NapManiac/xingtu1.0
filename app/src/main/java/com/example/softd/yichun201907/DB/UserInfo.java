package com.example.softd.yichun201907.DB;

import org.litepal.crud.LitePalSupport;

public class UserInfo extends LitePalSupport {

    private String name = "";

    private String email = "";

    private String tel = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
