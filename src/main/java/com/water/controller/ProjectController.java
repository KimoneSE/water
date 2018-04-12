package com.water.controller;

import com.water.entity.Project;
import com.water.entity.ProjectUser;
import com.water.service.ProjectService;
import com.water.service.ProjectUserService;
import net.sf.json.JSONArray;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

//处理项目的增删改
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectUserService projectUserService;

    /**
     * @param request
     * @return 项目id
     * @throws Exception
     * 发布新项目
     */
    @RequestMapping("/publish")
    @ResponseBody
    public long publishProject(HttpServletRequest request) throws IOException {
        String head=request.getParameter("headline");
        String body=request.getParameter("body");
        int isPrivate=Integer.parseInt(request.getParameter("isPrivate"));
        String userList=request.getParameter("userAdded");
        Double lngMax=Double.parseDouble(request.getParameter("lngMax"));
        Double lngMin=Double.parseDouble(request.getParameter("lngMin"));
        Double latMax=Double.parseDouble(request.getParameter("latMax"));
        Double latMin=Double.parseDouble(request.getParameter("latMin"));
        Date date=new Date();
        Calendar ca = Calendar.getInstance();
        int curYear=ca.get(Calendar.YEAR);//当前年份
        String curId;//当前要生成的项目编号
        if (projectService.findIdMax()==null){//如果数据库里项目编号为空，没有最大值
            curId=Integer.toString(curYear)+"0001";
        }
        else {
            long idMax=projectService.findIdMax();//数据库里项目编号最大值
            String yearMax=Long.toString(idMax).substring(0,4);//年份最大值
            if (curYear>Integer.parseInt(yearMax)){//如果当前年份大于数据库年份最大值
                curId=Integer.toString(curYear)+"0001";
            }
            else {
                curId=Long.toString(idMax+1);
            }
        }
        Long id=Long.parseLong(curId);

        Project project=new Project();
        project.setIdProject(id);
        project.setName(head);
        project.setDescription(body);
        project.setState(0);
        project.setDate(date);
        project.setIsPrivate(isPrivate);
        project.setLngMax(lngMax);
        project.setLngMin(lngMin);
        project.setLatMax(latMax);
        project.setLatMin(latMin);

        boolean success=projectService.saveProject(project);

        boolean success2=true;
        if(isPrivate==1){
            success2=projectUserService.add(userList,id);
        }

        if (!success || !success2){
            return -1;
        }
        return id;
    }

    /**
     * @param response
     * @return 项目列表
     * @throws Exception
     * 获取所有项目列表
     */
    @RequestMapping("/allProject")
    @ResponseBody
    public void getAll(HttpServletResponse response) throws IOException {
        List<Project> projects=projectService.findAllProjects();
        JSONArray jsonObject=JSONArray.fromObject(projects);

        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonObject);
        response.getWriter().flush();
    }

    /**
     * @param response
     * @return 项目列表
     * @throws Exception
     * 获取所有项目的名字
     */
    @RequestMapping("/getProjectName")
    @ResponseBody
    public void getProjectName(HttpServletResponse response) throws IOException {
        List<Project> projects=projectService.findAllProjects();
        ArrayList<String> projectNames = new ArrayList<String>();
        for(Project temp:projects){
            projectNames.add(String.valueOf(temp.getName()+""));
        }
        JSONArray array = JSONArray.fromObject(projectNames);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    /**
     * @param request
     * @return boolean
     * @throws Exception
     * 删除项目
     */
    @RequestMapping("/deleteProject")
    @ResponseBody
    public boolean delete(HttpServletRequest request) throws IOException {
        String id=request.getParameter("id");
        boolean success=projectService.deleteProject(Long.parseLong(id));
        return success;
//        return true;
    }

    /**
     * @param request
     * @return boolean
     * @throws Exception
     * 修改项目
     */
    @RequestMapping("/modifyProject")
    @ResponseBody
    public int modify(HttpServletRequest request) throws IOException {
        long id=Long.parseLong(request.getParameter("id"));
        System.out.println(id);
        String head=request.getParameter("headline");
        String body=request.getParameter("body");
        System.out.println(body);
        int isPrivate=Integer.parseInt(request.getParameter("isPrivate"));
        String userList=request.getParameter("userAdded");
        Double lngMax=Double.parseDouble(request.getParameter("lngMax"));
        Double lngMin=Double.parseDouble(request.getParameter("lngMin"));
        Double latMax=Double.parseDouble(request.getParameter("latMax"));
        Double latMin=Double.parseDouble(request.getParameter("latMin"));
//        Date date=new Date();

        Project project=new Project();
        project.setIdProject(id);
        project.setName(head);
        project.setDescription(body);
//        project.setDate(date);
        project.setIsPrivate(isPrivate);
        project.setLngMax(lngMax);
        project.setLngMin(lngMin);
        project.setLatMax(latMax);
        project.setLatMin(latMin);

        boolean success=projectService.modifyProject(project);

        boolean success2=true;
        if(isPrivate==1){
            success2=projectUserService.modify(userList,id);
        }

        if (!success){
            return 2;
        }
        if (!success2){
            return 3;
        }
        return 1;
//        if (!success || !success2){
//            return false;
//        }
//        return true;
    }

    /**
     * @param request
     * @param response
     * @return 上传项目报告
     * @throws Exception
     */
    @RequestMapping("/uploadProjectResult")
    public void uploadProjectResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String project = request.getParameter("project");
//            String projectname = request.getParameter("projectname");
            Project p = projectService.findProjectByName(project);
            if(p==null) {
                response.getWriter().print("该项目不存在");
            }else {
                response.getWriter().print("success");
            }
//            else{
//                response.getWriter().print("");
//                String filename =projectname+".pdf";
//                boolean bool = projectService.uploadProject(project,filename);
//                if(bool)
//                    response.getWriter().print("上传成功");
//                else {
//                    response.getWriter().print("上传失败");
//                }
//            }

    }
    /**
     * @param request
     * @return 上传项目报告文件
     * @throws Exception
     */
    @RequestMapping("/uploadProjectFile")
    @ResponseBody
    public JSONObject uploadProjectFile(@RequestParam("file") MultipartFile image, HttpServletRequest request) throws IOException {
        String filename =  request.getParameter("filename");
        String projectName = request.getParameter("projectName");


        File dir=new File("/home/web_upload/");
        MultipartFile file = image;
        if( !(file.getOriginalFilename().equals("")) ) {
            boolean bool = projectService.uploadProject(projectName,filename+".pdf");
            if(bool){
                file.transferTo(new File("/home/web_upload/"+filename+".pdf"));
                String json = "{'state':'success'}";
                JSONObject object = JSONObject.fromObject(json);
                return object;
            }

        }

        return null;
    }

    /**
     * @param response
     * @return 最近一次项目
     * @throws Exception
     */
    @RequestMapping("/getLatestPro")
    @ResponseBody
    public JSONObject getLatest(HttpServletResponse response) throws IOException {
        Project project=projectService.findNewestProject();
//        Project project=new Project();
//        project.setName("北戴河");
//        project.setDescription("这是具体内容");
//        project.setIdProject(1);

        JSONObject jsonObject=JSONObject.fromObject(project);
        return jsonObject;
    }

    /**
     * @param request
     * @param response
     * @return 根据私有项目的projectID得到用户列表
     * @throws Exception
     */
    @RequestMapping("/getUserList")
    @ResponseBody
    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long projectID=Long.parseLong(request.getParameter("projectID"));
        List<ProjectUser> list=projectUserService.findByProjectID(projectID);
        String userList="";
        for (int i=0;i<list.size();i++) {
            userList=userList+list.get(i).getUserID()+"; ";
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(userList);
    }

}
