package com.water.dao.Impl;

import com.water.dao.ProjectUserDao;
import com.water.entity.ProjectUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimone.
 */
@Repository
public class ProjectUserDaoImpl implements ProjectUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public boolean add(ProjectUser projectUser) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            session.save(projectUser);
            tx.commit();
            flag = true;
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean deleteByProjctID(long projectID) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            Query query = session.createQuery("delete from ProjectUser where projectID =:projectID");
            query.setLong("projectID", projectID);
            System.out.println(projectID);
            query.executeUpdate();
            tx.commit();
            flag = true;
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List<ProjectUser> findByProjectID(long projectID) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<ProjectUser> list = new ArrayList<>();
        try{
            String hql = "from ProjectUser where projectID =:projectID";//使用命名参数，推荐使用，易读。
            Query query = session.createQuery(hql);
            query.setLong("projectID", projectID);
            list = query.list();
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
        }finally {
            session.close();
        }
        return list;
    }

}
