package com.water.service.Impl;

import com.water.dao.ApplyDao;
import com.water.dao.UploadDao;
import com.water.entity.Apply;
import com.water.entity.Sample;
import com.water.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created by 朱晨乾 on 2017/7/17.
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadDao uploadDao;

    @Autowired
    private ApplyDao applyDao;

    @Override
    public boolean addUpload(Sample sample) {
        return uploadDao.save(sample);
    }

    @Override
    public Sample searchSample(Long idSample) {
        Sample sample = uploadDao.get(idSample);
        return sample;
    }

    @Override
    public ArrayList<Sample> alreadySample(String userid) {
        ArrayList<Sample> resultlist = new ArrayList<Sample>();
        List<Apply> list = applyDao.findApplyById(userid);
        for (Apply temp : list) {
            Sample sample=uploadDao.findSampleById(temp.getIdApply());
            if (sample!=null){
                resultlist.add(sample);
            }
        }

        Collections.sort(resultlist, new Comparator<Sample>() {

            @Override
            public int compare(Sample o1, Sample o2) {
                int flag = -o1.getSampleDate().compareTo(o2.getSampleDate());
                return flag;
            }
        });

        return resultlist;
    }

    @Override
    public ArrayList<Sample> findAll() {

        ArrayList<Sample> list = new ArrayList<Sample>();
        for (Sample temp : uploadDao.findAll()) {
            list.add(temp);

        }
        return list;
    }

    @Override
    public int judgeByID(long id){
        int state = -1;
        Sample sample = uploadDao.get(id);
        if(sample==null) {
            state = -1;
        }
        else {
            state=sample.getState();
        }
        return state;
    }

    @Override
    public boolean updateSample(long idSample, int state){
        Sample sample = uploadDao.get(idSample);
        if(sample==null)
            return false;
        sample.setState(state);
        return uploadDao.saveOrUpdate(sample);
    }

    //根据项目编号和日期筛选sample
//    public ArrayList<Sample> filterSample(String eventID,String startDate,String endDate){
//        List<Sample> list=uploadDao.findAll();
//        ArrayList<Sample> result = new ArrayList<Sample>();
//        if(list==null
//                ){
//            result = null;
//        }
//        else {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
//
//            try {
//                Date date1 = sdf.parse(startDate);
//                Date date2 = sdf.parse(endDate);
//                for (Sample temp : list
//                        ) {
//                    Date date = temp.getSampleDate();
//                    //数据库date转化可能会有问题
//                    if (date.getTime() >= date1.getTime() && date.getTime() <= date2.getTime()
//                            &&String.valueOf(temp.getApply().getProject().getIdProject()).equals(eventID)) {
//                            result.add(temp);
//                    }
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return  result;
//    }

    @Override
    public void addTxt(Sample sample) {
        try {
            long applyID = sample.getApplyID();
            Apply apply = applyDao.getApplyByID(applyID);
            // FileOutputStream file1 = new FileOutputStream("F:\\拙劣工程师\\water\\src\\main\\webapp\\resources\\txt\\new.txt");
            FileOutputStream file1 = new FileOutputStream("/home/samples/" + sample.getSample_id() + ".txt");
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(file1, "utf-8");
            String id = "样本编号： " + sample.getSample_id() + "\r\n";
            String name = "申请人姓名： " + apply.getName() + "\r\n";
            String applyid = "申请编号：  " + apply.getIdApply() + "\r\n";
            String applytime = "申请时间： " + apply.getApplyDate() + "\r\n";
            String sampletime = "采样时间： " + sample.getSampleDate() + "\r\n";
            String remark = "备注： " + sample.getRemark() + "\r\n";
            String volume = "体积： " + sample.getVolume() + "\r\n";
            String phone = "联系方式： " + apply.getNumber() + "\r\n";
            String address = "水域地址： " + apply.getWaterAddress() + "\r\n";
            String lon = "经度： " + apply.getLongitude() + "\r\n";
            String lan = "纬度： " + apply.getLatitude() + "\r\n";
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
            oStreamWriter.flush();
            oStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
