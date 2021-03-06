package com.water.service.Impl;

import com.water.dao.NewsDao;
import com.water.entity.News;
import com.water.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kimone.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public void addNews(String title, String cover, String newsDoc) {
        News news = new News();
        news.setTitle(title);
        news.setCover(cover);
        news.setNewsDoc(newsDoc);
        newsDao.add(news);
    }

    @Override
    public News getByID(long id) {
        return newsDao.getByID(id);
    }

    @Override
    public List<News> getNews() {
        return newsDao.getNews();
    }

    @Override
    public List<News> getAllNews() {
        return newsDao.getAllNews();
    }
}
