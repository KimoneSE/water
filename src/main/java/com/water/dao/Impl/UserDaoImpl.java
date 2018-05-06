package com.water.dao.Impl;

import com.water.dao.UserDao;
import com.water.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
/**
 * Created by asus1 on 2017/7/19.
 */
@Repository
public class UserDaoImpl implements UserDao{


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public User load(String id) {
        Session session = getCurrentSession();
        User user = (User) session.load(User.class, id);
        session.close();
        return user;
    }

    public User get(String id) {
        Session session = getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public void persist(User entity) {
        Session session = getCurrentSession();
        session.persist(entity);
        session.close();
    }

    public boolean save(User entity) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
         boolean flag = false;
        try{
            session.save(entity);
            tx.commit();
            flag = true;
        }catch(Exception ex){
            tx.rollback();
        }finally{
            session.close();
        }
        return flag;
    }

    public boolean saveOrUpdate(User entity) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try{
            session.saveOrUpdate(entity);
            tx.commit();
            flag = true;
        }catch(Exception ex){
            tx.rollback();
        }finally{
            session.close();
        }
        return flag;
    }

    public boolean delete(String id) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            User user = (User) session.load(User.class, id);
            session.delete(user);
            tx.commit();
            flag = true;
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    public void flush() {
        Session session = getCurrentSession();
        session.flush();
        session.close();
    }

    @Override
    public User findUserByNumber(String number) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from `User` where number="+number);
        if(query.list()==null||query.list().size() == 0){
            session.close();
            return null;
        }else{
            User user = (User)query.list().get(0);
            session.close();
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("from User");
        List<User> list = query.list();
        session.close();
        return list;
    }
}