package com.wangyang.pojo;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LoveWall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //User ID
    private int uId;
    private int jsName;



    private String title;
    private String content;
    @Column(columnDefinition = "TEXT")
    private String canvas;
    private String email;
    private String url;
    private Date date;

    public int getJsName() {
        return jsName;
    }

    public void setJsName(int jsName) {
        this.jsName = jsName;
    }
    //like quantity
    private String likeNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getCanvas() {
        return canvas;
    }

    public void setCanvas(String canvas) {
        this.canvas = canvas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
