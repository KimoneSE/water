package com.water.controller;

/**
 * Created by Administrator on 2017/7/25 0025.
 */
import com.sun.org.apache.regexp.internal.RE;
import com.water.entity.Apply;
import com.water.entity.Result;
import com.water.entity.Sample;
import com.water.service.*;
import com.water.service.Impl.ApplyServiceImpl;
import com.water.vo.SampleVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class sampleController {

    protected Logger log = Logger.getLogger(sampleController.class);
    @Autowired
    private ResultService resultService;
//    @Autowired
//    private UploadService uploadService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private ApplyService applyService;

    /**
     * @param request
     * @return 上传实验结果的图片
     * @throws Exception
     */
    @RequestMapping(value = "/uploadSampleResultImg",produces = "application/json;charset=utf8")
    @ResponseBody
    public JSONObject uploadImg(@RequestParam("file") MultipartFile[] image,HttpServletRequest request) throws IOException {
        System.out.println("asdsad");
        System.out.println(request.getParameter("idSample")+"$$$$$$$$");
        String filename = request.getParameter("idSample");
        File dir=new File("/home/samples/"+filename);
        if(!dir.exists()){
            dir.mkdirs();
        }
        for(int i=0;i<image.length;i++){
            MultipartFile file = image[i];
            if( !(file.getOriginalFilename().equals("")) ) {
                file.transferTo(new File("/home/web_upload/"+filename+"/" + file.getOriginalFilename()));
            }
        }
        String json = "{'state':'success'}";
        JSONObject object = JSONObject.fromObject(json);
        return object;
    }
    /**
     * @param request
     * @param response
     * @return 上传实验结果
     * @throws Exception
     */
    @RequestMapping("/uploadResult")
    @ResponseBody
    public void uploadResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idSample=request.getParameter("idSample");
        String text = request.getParameter("description");
        System.out.println(idSample);
        Result result  = new Result();
        result.setIdResult(Integer.valueOf(idSample));
        result.setDescription(text);
     //   result.setImage(nameList);
       boolean bool= resultService.addResult(result);
       if(bool){
           boolean bool1 = sampleService.updateSampleState(Long.valueOf(idSample),2);
       }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("success");
    }

    /**
     * @param request
     * @param response
     * @return 修改样本实验结果
     * @throws Exception
     */
    @RequestMapping("/modifyResult")
    @ResponseBody
    public void modifyResult(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idSample=request.getParameter("idSample");
        String text = request.getParameter("description");
        boolean bool= resultService.modifyResult(Long.valueOf(idSample),text);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(bool);
    }

    /**
     * @param request
     * @param response
     * @return 改变样本状态
     * @throws Exception
     */
    @RequestMapping("/sampleState")
    @ResponseBody
    public void sampleState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idSample=request.getParameter("idSample");
        String state = request.getParameter("state");
        boolean bool = sampleService.updateSampleState(Long.valueOf(idSample),Integer.valueOf(state));
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(bool);
    }
    /**
     * @param request
     * @param response
     * @return 根据经纬度返回样本
     * @throws Exception
     */
    @RequestMapping("/getSampleReport")
    @ResponseBody
    public void getSampleReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String longtitude=request.getParameter("longtitude");
        String latitude= request.getParameter("latitude");

        Result result = resultService.getResultbyLocation(Double.valueOf(longtitude),Double.valueOf(latitude));
        JSONObject jsonObject = JSONObject.fromObject(result);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonObject.toString());
    }
    /**
     * @param request
     * @param response
     * @return 获得所有样本编号
     * @throws Exception
     */
    @RequestMapping("/getSampleID")
    @ResponseBody
    public void getSampleID(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Sample> samples = sampleService.findAll();
        ArrayList<String> sampleIDs = new ArrayList<String>();
        for(Sample temp:samples){
            sampleIDs.add(String.valueOf(temp.getSampleID()+""));
        }
        log.info("test1");
        log.info("test2");
        log.info("test3");
        JSONArray array = JSONArray.fromObject(sampleIDs);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    /**
     * @param request
     * @param response
     * @return 根据ID得到实验结果
     * @throws Exception
     */
    @RequestMapping("/getSampleResult")
    @ResponseBody
    public void getSampleResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idSample=request.getParameter("idSample");
        if(resultService.findResultByID(Long.valueOf(idSample))!=null){
            String result = resultService.findResultByID(Long.valueOf(idSample)).getDescription();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(result);
        }
    }
    /**
     * @param request
     * @param response
     * @return 获得样本信息
     * @throws Exception
     */
    @RequestMapping("/getSample")
    public void getSample(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Long id1 = Long.valueOf(id);
        Sample sample = sampleService.getSampleBySampleID(id1);
        if(sample!=null){
            Apply apply = applyService.searchApplication(sample.getApplyID());
            SampleVO vo = convert2VO(sample,apply);
            sampleService.addTxt(sample);
            JSONObject object = JSONObject.fromObject(vo);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(object.toString());
        }else {
            JSONObject object = JSONObject.fromObject(null);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(object.toString());
        }
    }

    private SampleVO convert2VO(Sample sample, Apply apply) {
        SampleVO vo = new SampleVO();
        vo.setRemark(sample.getRemark());
        vo.setSampleDate(sample.getSampleDate());
        vo.setSampleID(sample.getSampleID());
        vo.setState(sample.getState());
        vo.setTemperature(sample.getTemperature());
        vo.setVolume(sample.getVolume());
        vo.setWeather(sample.getWeather());
        vo.setAmmoniaN_c(sample.getAmmoniaN_c());
        vo.setPhosphate_c(sample.getPhosphate_c());
        vo.setApply(apply);
        String projectName = apply.getProject().getName();
        vo.setProjectName(projectName);
        vo.setProjectID(apply.getProject().getIdProject());
        return vo;
    }
    /**
     * @param request
     * @param response
     * @return 该样本是否可以上传实验结果
     * @throws Exception
     */
    @RequestMapping("/getSampleResultTest")
    public void sampleResultTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Long id1 = Long.valueOf(id);
        int res = sampleService.judgeByID(id1);
        response.getWriter().print(String.valueOf(res));

    }

    /**
     * @param request
     * @param response
     * @return 获得所有样本信息
     * @throws Exception
     */
    @RequestMapping("/getSampleList")
    public void getSampleList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Sample> samples = sampleService.findAll();
        log.error("asdk");
        if (samples.size() > 0)
            sampleService.addTxt(samples.get(0));
        List<SampleVO> vos = new ArrayList<>();
        for(Sample sample : samples) {
            Apply apply = applyService.searchApplication(sample.getApplyID());
            SampleVO vo = convert2VO(sample,apply);
            vos.add(vo);
        }
        JSONArray array = JSONArray.fromObject(vos);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    /**
     * 获取某个项目的所有样本信息
     * @param request
     * @param response
     */
    @RequestMapping("/getSamplesByProID")
    public void getSamplesByProID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long projectID = Long.parseLong(request.getParameter("projectID"));
        List<Sample> samples = sampleService.getSamplesByProject(projectID);
        JSONArray array = JSONArray.fromObject(samples);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
        long projectID = Long.parseLong(request.getParameter("projectID"));
        String filename = sampleService.writeXlsx(projectID);
        System.out.println("******"+filename+"*******");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(filename);
        //        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment; filename="+filename);
    }
}
