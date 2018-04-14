package com.water.service.Impl;

import com.water.dao.ProjectUserDao;
import com.water.entity.ProjectUser;
import com.water.service.ProjectUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kimone.
 */
@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectUserDao projectUserDao;

    @Override
    public boolean add(String userList,long projectID) {
        boolean success=true;
        if(userList.contains(";")){
            String[] list=userList.split(";");
            String temp;
            for (int i=0;i<list.length;i++){
                temp = list[i].trim();
                if (temp.length()!=0){
                    ProjectUser projectUser = new ProjectUser();
                    projectUser.setProjectID(projectID);
                    projectUser.setUserID(temp);
                    if (!projectUserDao.add(projectUser)) {
                        success=false;
                        break;
                    }
                }
            }
        }
        return success;
    }

    @Override
    public boolean modify(String userList,long projectID) {
        if (!projectUserDao.deleteByProjctID(projectID)) {
            return false;
        }
        return add(userList,projectID);
    }

    @Override
    public List<ProjectUser> findByProjectID(long projectID) {
        return projectUserDao.findByProjectID(projectID);
    }

    @Override
    public boolean deleteByProjectID(long projectID) {
        return projectUserDao.deleteByProjctID(projectID);
    }
}
