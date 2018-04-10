package com.water.dao.Impl;

import com.water.dao.NewsDao;
import com.water.entity.News;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public News getByID(long id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from News where id="+id);
        List list = query.list();
        session.close();
        if(list.size()>0){
            return (News) list.get(0);
        }
        return null;
    }

    @Override
    public List<News> getNews() {
        Session session = getCurrentSession();
        Query query = session.createQuery("from News order by id desc");
        query.setMaxResults(5);
        List<News> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<News> getAllNews() {
        Session session = getCurrentSession();
        Query query = session.createQuery("from News order by id desc");
        List<News> list = query.list();
        session.close();
        return list;
    }
}
