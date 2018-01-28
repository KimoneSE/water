package com.water.service;

import com.water.entity.Sample;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface SampleService {
    public void add(Sample sample);

    public boolean update(Sample sample);

    public Sample getInvalidSampleByApplyID(long applyID);

    public List<Sample> alreadySample(String userID);

    public  int countInvalidSample(long applyID);

    public Sample getSampleBySampleID(long sampleID);

    public List<Sample> findAll();

    public int judgeByID(long sampleID);
}
