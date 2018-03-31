package com.water.dao.Impl;

import com.water.dao.NewsDao;
import com.water.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Kimone.
 */
@Repository
public class NewsDaoImpl implements NewsDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }


    @Override
    public void add(News news) {
        Session session = getCurrentSession();
        session.save(news);
        session.flush();
        session.close();
    }
}
