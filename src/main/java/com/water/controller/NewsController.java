package com.water.controller;

import com.water.entity.News;
import com.water.service.NewsService;
import com.water.util.Convert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public void saveNews(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String cover = request.getParameter("cover");
        String word = request.getParameter("word");
        String filename = request.getParameter("filename");

        String coverExt = cover.substring(cover.lastIndexOf("."));
        String wordExt = word.substring(word.lastIndexOf("."));

        String coverName = filename+coverExt;
        String wordName = filename+wordExt;
        newsService.addNews(title, coverName, wordName);

//        String basePath = "/home/web_upload/";
//        String sourceFileName = basePath+wordName;
//        String targetFileName = basePath+filename+".html";
//        response.setCharacterEncoding("UTF-8");
//        if(wordExt.equals(".doc")) {
//            Convert.docToHtml(sourceFileName, targetFileName);
//            response.getWriter().print("doc");
//        }
//        if(wordExt.equals(".docx")) {
//            String s=Convert.docxToHtml(sourceFileName, targetFileName);
//            response.getWriter().print(s);
//        }else {
//
//        }
        response.getWriter().print("上传成功！");

    }

    @RequestMapping("/getIndexNews")
    @ResponseBody
    public void getIndexNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<News> list = newsService.getNews();
        JSONArray jsonObject=JSONArray.fromObject(list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping("/getAllNews")
    public ModelAndView getAllNews(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("../public/news");
        List<News> list = newsService.getAllNews();
        modelAndView.addObject("newsList", list);
        return modelAndView;
    }

    @RequestMapping("/concrete_news")
    public ModelAndView getNewsByID(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("../public/newsDetail");
        long id = Long.parseLong(request.getParameter("id"));

        News news = newsService.getByID(id);

        String basePath = "/home/web_upload/";
//        String basePath = "F:\\ac\\";
        String sourceFileName = basePath+news.getNewsDoc();
        String[] strings = news.getNewsDoc().split("\\.");
        String time = strings[0];
        String suffix = strings[1];
        String targetFileName = basePath+time+".html";
//        System.out.println(sourceFileName);
//        System.out.println(targetFileName);
        File file = new File(targetFileName);
        if(!file.exists()) {
            if(suffix.equals("doc")) {
                Convert.docToHtml(sourceFileName, targetFileName);
            }
            if(suffix.equals("docx")) {
                Convert.docxToHtml(sourceFileName, targetFileName);
            }
        }

        String html = Convert.readfile(targetFileName);
        String style = Convert.getStyle(html);
        String body = Convert.getBody(html);
//        modelAndView.addObject("newsHtml", html);
        modelAndView.addObject("style", style);
        modelAndView.addObject("body", body);
        return modelAndView;
    }
}
