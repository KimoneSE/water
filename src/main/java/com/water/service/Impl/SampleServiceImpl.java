package com.water.service.Impl;

import com.water.dao.ApplyDao;
import com.water.dao.SampleDao;
import com.water.entity.Apply;
import com.water.entity.Sample;
import com.water.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimone.
 */
@Service
@Transactional
public class SampleServiceImpl implements SampleService{
    @Autowired
    private SampleDao sampleDao;

    @Autowired
    private ApplyDao applyDao;

    @Override
    public void add(Sample sample) {
        sampleDao.add(sample);
    }

    @Override
    public void update(Sample sample) {
        sampleDao.update(sample);
    }

    @Override
    public Sample getInvalidSampleByApplyID(long applyID) {
        return sampleDao.getInvalidSampleByApplyID(applyID);
    }

    @Override
    public List<Sample> alreadySample(String userID) {
        List<Sample> samples = new ArrayList<>();
        List<Apply> list = applyDao.findApplyById(userID);
        for (Apply apply : list) {
            Sample sample = sampleDao.getValidSampleByApplyID(apply.getIdApply());
            if(sample != null){
                samples.add(sample);
            }
        }
        return samples;
    }

    @Override
    public int countInvalidSample(long applyID) {
        return sampleDao.countInvalidSample(applyID);
    }
}
