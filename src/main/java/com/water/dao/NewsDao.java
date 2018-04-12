package com.water.dao;

import com.water.entity.News;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface NewsDao {
    public void add(News news);

    public News getByID(long id);

    public List<News> getNews();

    public List<News> getAllNews();
}
