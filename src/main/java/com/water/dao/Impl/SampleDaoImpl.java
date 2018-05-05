package com.water.dao.Impl;

import com.water.dao.SampleDao;
import com.water.entity.Sample;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kimone.
 */
@Repository
public class SampleDaoImpl implements SampleDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public void add(Sample sample) {
        Session session = getCurrentSession();
        session.save(sample);
        session.flush();
        session.close();
    }

    @Override
    public boolean update(Sample sample) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            session.update(sample);
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
    public Sample getInvalidSampleByApplyID(long applyID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Sample where applyID="+applyID+" and state=-1");
        Sample sample = (Sample)query.list().get(0);
        session.close();
        return sample;
    }

    @Override
    public Sample getValidSampleByApplyID(long applyID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Sample where applyID="+applyID+" and state!=-1");
        List list = query.list();
        session.close();
        if(list.size()!=0) {
            Sample sample = (Sample)list.get(0);
            return sample;
        }
        return null;
    }

    @Override
    public List<Sample> getSamplesByApplyID(long applyID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Sample where applyID="+applyID+" and state!=-1");
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public int countInvalidSample(long applyID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select count(*) from Sample where applyID="+applyID+" and state=-1");
        int count = ((Number)query.uniqueResult()).intValue();
        session.close();
        return count;
    }

    @Override
    public void deleteSampleByApplyID(long applyID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("delete from Sample where applyID="+applyID);
        query.executeUpdate();
        session.close();
    }

    @Override
    public Sample getSampleBySampleID(String sampleID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Sample where sampleID="+sampleID);
        List list = query.list();
        session.close();
        if(list.size()!=0){
            Sample sample = (Sample)list.get(0);
            return sample;
        }
        return null;
    }

    @Override
    public List<Sample> findAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Sample where state!=-1");
        List<Sample> list = query.list();
        session.close();
        return list;
    }

    @Override
    public String getMaxSampleID(String projectID) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Sample where sampleID like '"+projectID+"%' order by sampleID desc");
        List list = query.list();
        if (list.size()!=0){
            Sample sample = (Sample) list.get(0);
            return sample.getSample_id();
        }

        return null;
    }

}
