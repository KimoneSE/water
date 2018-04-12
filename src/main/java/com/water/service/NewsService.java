package com.water.service;

import com.water.entity.News;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface NewsService {
    public void addNews(String title, String cover, String newsDoc);

    public News getByID(long id);

    public List<News> getNews();

    public List<News> getAllNews();
}
