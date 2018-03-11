package com.water.controller;

import com.water.entity.Apply;
import com.water.entity.Sample;
import com.water.service.ApplyService;
import com.water.service.SampleService;
import com.water.service.UploadService;
import com.water.util.WeatherUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 上传采样信息页面的controller
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    private Logger logger = Logger.getLogger(UploadController.class);
    @Autowired
    ApplyService applyService;

    @Autowired
    private SampleService sampleService;

    @Autowired
    UploadService uploadService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 加载上传采样信息界面，先放入该申请的信息
     * @param applyID
     * @param request
     * @return
     */
    @RequestMapping("/j{applyID}")
    public ModelAndView uploadSamplePage(@PathVariable String applyID, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("../wx/upload_sampling_info");
        //设置采样编号
        long sampleID = Long.parseLong(request.getParameter("sampleID"));
        modelAndView.addObject("sampleID", sampleID);
        //设置当前时间
        Date date = new Date();
        String timeStr = sdf.format(date);
        String[] split_time = timeStr.split(" ");
        String curTime = split_time[0] + "T" + split_time[1];
        modelAndView.addObject("curTime", curTime);
        //获取申请信息
        Apply apply = applyService.searchApplication(Long.parseLong(applyID));
//        Apply apply=new Apply();
//        apply.setWaterAddress("123");apply.setLongitude(12.0);
//        apply.setLatitude(23.0);apply.setImage("/resources/img/delete.png");
        modelAndView.addObject("waterAddress", apply.getWaterAddress());
        String latitude = String.format("%.3f", Math.abs(apply.getLatitude()));
        if (apply.getLatitude() >= 0) {
            modelAndView.addObject("latitude", "北纬" + latitude + "度");
        } else {
            modelAndView.addObject("latitude", "南纬" + latitude + "度");
        }
        String longitude = String.format("%.3f",Math.abs(apply.getLongitude()));
        if (apply.getLongitude() >= 0) {
            modelAndView.addObject("longitude", "东经" + longitude + "度");
        } else {
            modelAndView.addObject("longitude", "西经" + longitude + "度");
        }
        //解析图片路径
        ArrayList<String> imageArray = new ArrayList<String>();
        if (apply.getImage() != null) {
            List<String> images = apply.getImage();
            for (String image : images) {
                if(!image.equals(""))
                    imageArray.add("http://www.ufengtech.xyz/web_upload/"+image);
            }
        }
        modelAndView.addObject("imageArray",imageArray);
        modelAndView.addObject("applyID", applyID);
        //通过session获得userID
//        HttpSession session=request.getSession();
//        String userID=(String)session.getAttribute("userID");
//        modelAndView.addObject("userID",userID);
        String userID=apply.getUser().getIdUser();
        modelAndView.addObject("userID",userID);
        modelAndView.addObject("username", apply.getName());
        //获取天气
        String real_longitude = String.format("%.2f", apply.getLongitude());
        String real_latitude = String.format("%.2f", apply.getLatitude());

        Map<String, String> map  = WeatherUtil.getWeather("nanjing");
        modelAndView.addObject("temperature", map.get("temperature"));
        modelAndView.addObject("weather", map.get("weather"));

        return modelAndView;
    }

    /**
     * 上传采样信息界面的确认按钮的响应
     * @param applyID
     * @param request
     * @return
     */
    @RequestMapping(value = "/j{applyID}/confirm", method = RequestMethod.GET)
    @ResponseBody
    public boolean addUpload(@PathVariable String applyID, HttpServletRequest request) {
        //把得到的时间转成Date类型
        String sample_time = request.getParameter("sample_time");
        String[] split_date = sample_time.split("T");
        sample_time = split_date[0] + " " + split_date[1];
        Date sample_date = null;
        try {
            sample_date = sdf.parse(sample_time);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        Double sample_volume = Double.parseDouble(request.getParameter("sample_volume"));
        long sampleID = Long.parseLong(request.getParameter("sample_number"));
        String sample_remark = request.getParameter("sample_remark");
        String weather = request.getParameter("weather");
        String tmp = request.getParameter("temperature");
        double temperature = Double.parseDouble(request.getParameter("temperature").substring(0,tmp.length()-1));
//        Apply apply=applyService.searchApplication(Long.parseLong(applyID));
        //构造一个Sample对象
        Sample sample = sampleService.getInvalidSampleByApplyID(Long.parseLong(applyID));
        sample.setSampleDate(sample_date);
        sample.setVolume(sample_volume);
        sample.setSampleID(sampleID);
        sample.setRemark(sample_remark);
        sample.setState(0);
        sample.setTemperature(temperature);
        sample.setWeather(weather);

        return sampleService.update(sample);
    }
}
