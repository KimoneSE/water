package com.water.service.Impl;

import com.water.dao.ApplyDao;
import com.water.dao.ProjectDao;
import com.water.dao.ResultDao;
import com.water.dao.SampleDao;
import com.water.entity.Apply;
import com.water.entity.Result;
import com.water.entity.Sample;
import com.water.service.SampleService;
import com.water.vo.SampleVO;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
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

    @Autowired
    private ProjectDao projectDao;

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
    public Sample getSampleBySampleID(String sampleID) {
        return sampleDao.getSampleBySampleID(sampleID);
    }

    @Override
    public List<Sample> findAll() {
        return sampleDao.findAll();
    }

    @Override
    public int judgeByID(String sampleID) {
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
    public boolean updateSampleState(String idSample, int state) {
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
            FileOutputStream file1 = new FileOutputStream("/home/samples/" + sample.getSample_id() + ".txt");
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(file1, "utf-8");
            String id = "样品编号：" + sample.getSample_id() + "\r\n";
            String sampleID = "样品瓶编号： " + sample.getBottleID() + "\r\n";
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
            String ammoniaN_c = "氨氮浓度" + sample.getAmmoniaN_c() + "\r\n";
            String phosphate_c = "磷酸盐浓度" + sample.getPhosphate_c() + "\r\n";
            String projectName = "所属项目" + apply.getProject().getName() + "\r\n";
            oStreamWriter.write(id);
            oStreamWriter.write(sampleID);
            oStreamWriter.write(name);
            oStreamWriter.write(applyid);
            oStreamWriter.write(applytime);
            oStreamWriter.write(sampletime);
            oStreamWriter.write(projectName);
            oStreamWriter.write(volume);
            oStreamWriter.write(ammoniaN_c);
            oStreamWriter.write(phosphate_c);
            oStreamWriter.write(address);
            oStreamWriter.write(remark);
            oStreamWriter.write(phone);
            oStreamWriter.write(lon);
            oStreamWriter.write(lan);
            oStreamWriter.write(temperature);
            oStreamWriter.write(weather);
            oStreamWriter.write(stateStr);
            if(state == 2) {
                Result result = resultDao.get(sample.getBottleID());//TODO
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

    @Override
    public String writeXlsx(long projectID) {
        List<Sample> samples = getSamplesByProject(projectID);
//        if(samples == null)
//            return null;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("sampleInfo");
        XSSFRow firstRow = sheet.createRow(0);
        String[] attributes = {"样品编号","样品瓶编号","所属项目","采样者姓名","联系方式","申请编号", "申请时间",
                "采样时间", "水域地址","经度","纬度","样本体积","氨氮浓度","磷酸盐浓度","温度","天气","样本状态","备注"};
        XSSFCell[] firstCells = new XSSFCell[attributes.length];
        for(int i = 0; i < attributes.length; i++) {
            firstCells[i] = firstRow.createCell(i);
            firstCells[i].setCellValue(new XSSFRichTextString(attributes[i]));
        }

        for(int i = 0; i < samples.size(); i++) {
            XSSFRow row = sheet.createRow(i+1);
            Sample sample = samples.get(i);
            Apply apply = applyDao.getApplyByID(sample.getApplyID());
            row.createCell(0).setCellValue(sample.getSample_id());
            row.createCell(1).setCellValue(sample.getBottleID());
            row.createCell(2).setCellValue(apply.getProject().getName());
            row.createCell(3).setCellValue(apply.getName());
            row.createCell(4).setCellValue(apply.getNumber());
            row.createCell(5).setCellValue(sample.getApplyID());
            row.createCell(6).setCellValue(apply.getApplyDate()+"");
            row.createCell(7).setCellValue(sample.getSampleDate()+"");
            row.createCell(8).setCellValue(apply.getWaterAddress());
            row.createCell(9).setCellValue(apply.getLongitude()+"°");
            row.createCell(10).setCellValue(apply.getLatitude()+"°");
            row.createCell(11).setCellValue(sample.getVolume()+"ml");
            row.createCell(12).setCellValue(sample.getAmmoniaN_c());
            row.createCell(13).setCellValue(sample.getPhosphate_c());
            row.createCell(14).setCellValue(sample.getTemperature()+"℃");
            row.createCell(15).setCellValue(sample.getWeather());
            String stateStr = "";
            int state = sample.getState();
            if(state == 1) {
                stateStr = "处理中";
            }else if(state == 2) {
                stateStr = "已上传实验结果";
            }else if(state == 0) {
                stateStr = "待收取";
            }
            row.createCell(16).setCellValue(stateStr);
            row.createCell(17).setCellValue(sample.getRemark());
        }

        String str = projectID+"_"+projectDao.get(projectID).getName();

        String path = "/home/tomcat/webapps/projectSample/"+projectID+".xlsx";
        File file = new File(path);
        File parent = file.getParentFile();
        if(!parent.exists()) {
            parent.mkdirs();
        }

        try {
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            wb.write(os);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    @Override
    public List<Sample> getSamplesByProject(long projectID) {
        List<Apply> applications = applyDao.getApplicationsByProID(projectID);
        List<Sample> sampleList = new ArrayList<>();
        for(Apply apply : applications) {
            long applyID = apply.getIdApply();
            List<Sample> samples = sampleDao.getSamplesByApplyID(applyID);
            sampleList.addAll(samples);
        }
        return sampleList;
    }

    @Override
    public String getMaxSampleID() {
        return sampleDao.getMaxSampleID();
    }
}
