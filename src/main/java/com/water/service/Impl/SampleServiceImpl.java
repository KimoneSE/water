package com.water.service.Impl;

import com.water.dao.ApplyDao;
import com.water.dao.ResultDao;
import com.water.dao.SampleDao;
import com.water.entity.Apply;
import com.water.entity.Result;
import com.water.entity.Sample;
import com.water.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

    @Autowired
    private ResultDao resultDao;

    @Override
    public void add(Sample sample) {
        sampleDao.add(sample);
    }

    @Override
    public boolean update(Sample sample) {
        return sampleDao.update(sample);
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
            List<Sample> sampleList = sampleDao.getSamplesByApplyID(apply.getIdApply());
            if(sampleList != null){
                for(Sample sample:sampleList){
                    samples.add(sample);
                }
            }
        }
        return samples;
    }

    @Override
    public int countInvalidSample(long applyID) {
        return sampleDao.countInvalidSample(applyID);
    }

    @Override
    public Sample getSampleBySampleID(long sampleID) {
        return sampleDao.getSampleBySampleID(sampleID);
    }

    @Override
    public List<Sample> findAll() {
        return sampleDao.findAll();
    }

    @Override
    public int judgeByID(long sampleID) {
        int state = -1;
        Sample sample = sampleDao.getSampleBySampleID(sampleID);
        if(sample==null) {
            state = -1;
        }
        else {
            state=sample.getState();
        }
        return state;
    }

    @Override
    public boolean updateSampleState(long idSample, int state) {
        Sample sample = sampleDao.getSampleBySampleID(idSample);
        if(sample==null){
            return false;
        }
        sample.setState(state);
        return sampleDao.update(sample);
    }

    @Override
    public void addTxt(Sample sample) {
        try {
            long applyID = sample.getApplyID();
            Apply apply = applyDao.getApplyByID(applyID);
            int state = sample.getState();
            String stateStr = "样品状态： ";
            if(state == 1) {
                stateStr += "处理中";
            }else if(state == 2) {
                stateStr += "已上传实验结果";
            }else if(state == 0) {
                stateStr += "待收取";
            }
            stateStr += "\r\n";
            // FileOutputStream file1 = new FileOutputStream("F:\\拙劣工程师\\water\\src\\main\\webapp\\resources\\txt\\new.txt");
            FileOutputStream file1 = new FileOutputStream("/home/samples/" + sample.getSampleID() + ".txt");
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(file1, "utf-8");
            String id = "样本编号： " + sample.getSampleID() + "\r\n";
            String name = "申请人姓名： " + apply.getName() + "\r\n";
            String applyid = "申请编号：  " + apply.getIdApply() + "\r\n";
            String applytime = "申请时间： " + apply.getApplyDate() + "\r\n";
            String sampletime = "采样时间： " + sample.getSampleDate() + "\r\n";
            String remark = "备注： " + sample.getRemark() + "\r\n";
            String volume = "体积： " + sample.getVolume() + "\r\n";
            String phone = "联系方式： " + apply.getNumber() + "\r\n";
            String address = "水域地址： " + apply.getWaterAddress() + "\r\n";
            String lon = "经度： " + apply.getLongitude() + "°\r\n";
            String lan = "纬度： " + apply.getLatitude() + "°\r\n";
            String temperature = "温度： " + sample.getTemperature() + "℃\r\n";
            String weather = "天气： " + sample.getWeather() + "\r\n";
            oStreamWriter.write(id);
            oStreamWriter.write(name);
            oStreamWriter.write(applyid);
            oStreamWriter.write(phone);
            oStreamWriter.write(applytime);
            oStreamWriter.write(sampletime);
            oStreamWriter.write(volume);
            oStreamWriter.write(address);
            oStreamWriter.write(lon);
            oStreamWriter.write(lan);
            oStreamWriter.write(remark);
            oStreamWriter.write(temperature);
            oStreamWriter.write(weather);
            oStreamWriter.write(stateStr);
            if(state == 2) {
                Result result = resultDao.get(sample.getSampleID());
                String resultStr = "实验结果： " + result.getDescription() + "\r\n";
                oStreamWriter.write(resultStr);
            }
            oStreamWriter.flush();
            oStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
