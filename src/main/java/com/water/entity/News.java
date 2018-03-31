package com.water.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kimone.
 */

@Entity
public class News {
    private long id;
    private String title;   //新闻标题
    private String cover;   //新闻封面图片的名称
    private String newsDoc; //新闻文档的名称

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNewsDoc() {
        return newsDoc;
    }

    public void setNewsDoc(String newsDoc) {
        this.newsDoc = newsDoc;
    }
}
