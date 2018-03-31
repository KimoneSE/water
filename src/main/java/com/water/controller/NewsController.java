package com.water.controller;

import com.water.entity.News;
import com.water.service.NewsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kimone.
 */

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;


    @RequestMapping("/uploadNewsImg")
    @ResponseBody
    public JSONObject uploadNewsImg(@RequestParam("file") MultipartFile image, HttpServletRequest request) throws IOException {
        File dir = new File("/home/web_upload/");
        if(!dir.exists()){
            dir.mkdirs();
        }
        String filename =  request.getParameter("filename");
        MultipartFile file = image;
        String originStr = file.getOriginalFilename();
        if (!(originStr.equals(""))) {
            int index = originStr.lastIndexOf(".");
            String extension = originStr.substring(index);
            file.transferTo(new File("/home/web_upload/"+filename+extension));
            String json = "{'state':'success'}";
            JSONObject object = JSONObject.fromObject(json);
            return object;
        }
        return null;
    }

    @RequestMapping("/uploadNewsFile")
    @ResponseBody
    public JSONObject uploadNewsFile(@RequestParam("file") MultipartFile image, HttpServletRequest request) throws IOException {
        String filename =  request.getParameter("filename");
        File dir = new File("/home/web_upload/");
        if(!dir.exists()){
            dir.mkdirs();
        }
        MultipartFile file = image;
        String originStr = file.getOriginalFilename();
        if(!originStr.equals("")) {
            int index = originStr.lastIndexOf(".");
            String extension = originStr.substring(index);
            file.transferTo(new File("/home/web_upload/"+filename+extension));
            String json = "{'state':'success','word':"+filename+extension+"}";
            JSONObject object = JSONObject.fromObject(json);
            return object;
        }
        return null;
    }

    @RequestMapping("/saveNews")
    @ResponseBody
    public void saveNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String cover = request.getParameter("cover");
        String word = request.getParameter("word");
        String filename = request.getParameter("filename");

        String coverExt = cover.substring(cover.lastIndexOf("."));
        String wordExt = word.substring(word.lastIndexOf("."));

        String coverName = filename+coverExt;
        String wordName = filename+wordExt;
        newsService.addNews(title, coverName, wordName);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("success");
    }

}
