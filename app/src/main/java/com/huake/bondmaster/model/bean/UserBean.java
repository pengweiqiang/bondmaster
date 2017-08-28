package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/8/28 09:49
 * @email pengweiqiang64@163.com
 * @description 用户信息
 * @Version
 */

public class UserBean {
    private String id;
    private String username;
    private String token;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
