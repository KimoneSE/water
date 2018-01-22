package com.water.dao;

import com.water.entity.Sample;

/**
 * Created by Kimone.
 */
public interface SampleDao {
    /**
     * 添加一个sample对象
     * @param sample
     */
    public void add(Sample sample);

    /**
     * 更新sample对象
     * @param sample
     */
    public void update(Sample sample);

    /**
     * 根据applyID获取一个state为-1的sample对象
     * @param applyID
     * @return
     */
    public Sample getInvalidSampleByApplyID(long applyID);

    /**
     * 根据applyID获取一个state不为-1的sample对象
     * @param applyID
     * @return
     */
    public Sample getValidSampleByApplyID(long applyID);

    /**
     * 根据applyID获取初始状态下的样品对象的数量
     * @param applyID
     * @return
     */
    public  int countInvalidSample(long applyID);

    /**
     * 根据applyID删除样品对象
     * @param applyID
     */
    public void deleteSampleByApplyID(long applyID);
}
