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

    public Sample getSampleBySampleID(String sampleID);

    public List<Sample> findAll();

    public int judgeByID(String sampleID);

    public boolean updateSampleState(String idSample, int state);

    public void addTxt(Sample sample);

    public String writeXlsx(long projectID);

    public List<Sample> getSamplesByProject(long projectID);

    public String getMaxSampleID(String projectID);
}
