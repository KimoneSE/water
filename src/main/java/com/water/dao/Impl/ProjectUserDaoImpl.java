package com.water.dao.Impl;

import com.water.dao.ProjectUserDao;
import com.water.entity.ProjectUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Kimone.
 */
@Repository
public class ProjectUserDaoImpl implements ProjectUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(ProjectUser projectUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(projectUser);
    }
}
