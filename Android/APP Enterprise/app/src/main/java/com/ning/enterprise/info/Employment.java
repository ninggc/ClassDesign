package com.ning.enterprise.info;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ning on 2017/6/6.
 */
public class Employment implements IInfo, Serializable {

    //enterprise name or jobhunter name
    private String name;
    private String title;
    //employee release time
    private String releaseTime;
    //employee end time
    private String endTime;
    //employee introduce
    private String introduce;

    public Employment() {
        this("", "", "", "", "");
    }

    public Employment(String name, String title, String releaseTime, String endTime, String introduce) {
        setName(name);
        setTitle(title);
        setReleaseTime(releaseTime);
        setEndTime(endTime);
        setIntroduce(introduce);
    }

    @Override
    public String toJSON() {
        return new Gson().toJson(this).toString();
    }

    @Override
    public String toString() {
        return toJSON();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
