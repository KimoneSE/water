package com.water.service.Impl;

import com.water.dao.ProjectUserDao;
import com.water.entity.ProjectUser;
import com.water.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kimone.
 */
@Service
public class ProjectUserServiceImpl implements ProjectUserService {
    @Autowired
    private ProjectUserDao projectUserDao;

    @Override
    public void add(long projectID, String userID) {
        ProjectUser projectUser = new ProjectUser();
        projectUser.setProjectID(projectID);
        projectUser.setUserID(userID);
        projectUserDao.add(projectUser);
    }
}
