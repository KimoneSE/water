package com.water.controller;

import com.water.entity.Apply;
import com.water.entity.Sample;
import com.water.service.ApplyService;
import com.water.service.SampleService;
import com.water.service.UploadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Created by 朱晨乾 on 2017/7/17.
 */
@Controller
public class ApplyController {

    protected Logger log = Logger.getLogger(ApplyController.class);

    @Autowired
    private ApplyService applyService;
    @Autowired
    private SampleService sampleService;

    /**
     * @param request
     * @param response
     * @return 得到申请的list
     * @throws Exception
     */
    @RequestMapping("/applylist")
    @ResponseBody
    public void applylist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        log.error("!!!" + state + "!!!");
        int state1 = 0;
        if (("审核通过").equals(state))
            state1 = 1;
        if (("未通过审核").equals(state))
            state1 = 2;
        ArrayList<String> arrayList = applyService.getApplicationList(state1);
        JSONArray array = JSONArray.fromObject(arrayList);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    /**
     * @param request
     * @param response
     * @return 得到一个申请的信息
     * @throws Exception
     */
    @RequestMapping("/getApplyInfo")
    public void getSampleInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        long id1 = Integer.parseInt(id);
        Apply apply = applyService.searchApplication(id1);
        JSONObject object = JSONObject.fromObject(apply);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }

    /**
     * @param request
     * @param response
     * @return 审核申请
     * @throws Exception
     */
    @RequestMapping("/dealApply")
    public void dealApply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String state = request.getParameter("state");
        String reason = request.getParameter("response");
        int state1 = Integer.parseInt(state);
        long id1 = Integer.valueOf(id);
        boolean bool = applyService.updateState(id1, state1,reason);

        Apply apply = applyService.searchApplication(id1);
        int sampleNum = apply.getSampleNum();

        //构造样品对象
        for(int i = 0; i < sampleNum; i++) {
            Sample sample = new Sample();
            sample.setApplyID(apply.getIdApply());
            sample.setState(-1);

            String projectID = apply.getProject().getIdProject()+"";

            String max = sampleService.getMaxSampleID(projectID);
            int last4 = 0;
            if(max==null || max.equals("")) {
                last4 = 1;
            } else {
                last4 = Integer.valueOf(max.substring(max.length()-4))+1;
            }

            String tmp = last4+"";
            while(tmp.length()<4){
                tmp = "0"+tmp;
            }
            String sampleID = projectID + tmp;
            sample.setSample_id(sampleID);
            sampleService.add(sample);
        }

        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("处理成功");
    }


}
