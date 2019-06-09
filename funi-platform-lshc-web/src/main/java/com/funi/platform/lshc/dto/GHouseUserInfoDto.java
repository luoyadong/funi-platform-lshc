package com.funi.platform.lshc.dto;

/**
 * Created by sam on 2019/1/25.4:53 PM
 */
public class GHouseUserInfoDto {
    /** 系统用户ID*/
    private String id;
    /** 用于展示于页面的房管员名称*/
    private String name;
    /** 用户代码*/
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
